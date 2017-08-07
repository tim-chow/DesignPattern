import java.util.*;

interface Expression {
    int interpret(Map<String, Map<Character, Integer>> context);
    int getPriority(Map<String, Map<Character, Integer>> context);
}

class NumberExpression implements Expression {
    private Character chineseNumber;

    public NumberExpression(Character chineseNumber) {
        this.chineseNumber = chineseNumber;
    }

    public int interpret(Map<String, Map<Character, Integer>> context) {
        return context.get("numbers").get(chineseNumber);
    }

    public int getPriority(Map<String, Map<Character, Integer>> context) {
        return 1;
    }
}

class UnitExpression implements Expression {
    private Character unit;
    private List<Expression> lefts;

    public UnitExpression(Character unit, List<Expression> lefts) {
        this.unit = unit;
        this.lefts = lefts;
    }

    public int interpret(Map<String, Map<Character, Integer>> context) {
        int base = 0;
        for (Expression left: lefts) 
            base += left.interpret(context);

        return base * context.get("units").get(unit);
    }
    
    public int getPriority(Map<String, Map<Character, Integer>> context) {
        return context.get("units").get(unit);
    }
}

class Converter {
    public static final Map<String, Map<Character, Integer>> context = 
        new HashMap<>();

    static {
        Map<Character, Integer> numbers = new HashMap<>();
        numbers.put('零', 0);
        numbers.put('一', 1);
        numbers.put('二', 2);
        numbers.put('三', 3);
        numbers.put('四', 4);
        numbers.put('五', 5);
        numbers.put('六', 6);
        numbers.put('七', 7);
        numbers.put('八', 8);
        numbers.put('九', 9);
        context.put("numbers", numbers);

        Map<Character, Integer> units = new HashMap<>();
        units.put('十', 10);
        units.put('百', 100);
        units.put('千', 1000);
        units.put('万', 10000);
        units.put('亿', 100000000);
        context.put("units", units);
    }

    public int convert(String chineseNumber) {
        Stack<Expression> stack = new Stack<>();
        for (int i=0; i<chineseNumber.length(); ++i) {
            Character character = chineseNumber.charAt(i);
            if ('零' == character)
                continue;

            if (context.get("numbers").containsKey(character)) {
                stack.push(new NumberExpression(character));
                continue;
            }

            if (context.get("units").containsKey(character)) {
                List<Expression> lefts = new ArrayList<>();

                while (stack.size() > 0 && stack.peek().getPriority(context) 
                    <= context.get("units").get(character))
                    lefts.add(stack.pop());

                stack.push(new UnitExpression(character, lefts));
                continue;
            }
        }

        int sum = 0;
        for (Expression expression: stack)
            sum += expression.interpret(context);
        return sum;
    }
}

class InterpreterPattern {
    public static void main(String[] args) {
        System.out.println(new Converter().convert("五亿三十三万三千三百三十三"));
    }
}

