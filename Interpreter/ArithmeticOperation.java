/*
 * 计算a + b - c
 */

import java.util.*;

enum Operator {
    add, sub, mul, div;
}

interface Expression {
    Integer execute(Map<String, Integer> context);
}

class NumberExpression implements Expression {
    private String variableName;

    public NumberExpression(String variableName) {
        this.variableName = variableName;
    }

    public Integer execute(Map<String, Integer> context) {
        return context.get(variableName);
    }
}

class OperatorExpression implements Expression {
    private Operator operator;
    private Expression left;
    private Expression right;

    public OperatorExpression(Operator operator,
            Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Integer execute(Map<String, Integer> context) {
        Integer result;
        switch (operator) {
            case add:
                result = left.execute(context) + right.execute(context);
                break;
            case sub:
                result = left.execute(context) - right.execute(context);
                break;
            case mul:
                result = left.execute(context) * right.execute(context);
                break;
            case div:
                result = left.execute(context) / right.execute(context);
                break;
            default:
                result = 0;
        }

        return result;
    }
}

class Calculate {
    private Map<String, Integer> context;

    public Calculate(Map<String, Integer> context) {
        this.context = context;
    }

    public Integer calculate() {
        Stack<Expression> stack = new Stack<>();
        Expression left;

        stack.push(new NumberExpression("a"));
        left = stack.pop();
        stack.push(new OperatorExpression(Operator.add, left, 
            new NumberExpression("b")));
        left = stack.pop();
        stack.push(new OperatorExpression(Operator.sub, left,
            new NumberExpression("c")));

        return stack.pop().execute(context);
    }
}

public class ArithmeticOperation {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 30);
        map.put("b", 40);
        map.put("c", 50);
        System.out.println(new Calculate(map).calculate());
    }
}

