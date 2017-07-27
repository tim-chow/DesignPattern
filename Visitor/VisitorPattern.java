import java.util.*;

interface Visitor {
    Object visitAppleElement(AppleElement element);
    Object visitBookElement(BookElement element);
}

class Consumer implements Visitor {
    public Object visitAppleElement(AppleElement element) {
        return (new Date()).after(element.getExpireDate()) &&
            element.isPerfect();
    }

    public Object visitBookElement(BookElement element) {
        return element.isPerfect();
    }
}

class Cashier implements Visitor {
    public Object visitAppleElement(AppleElement element) {
        return element.getPrice();
    }

    public Object visitBookElement(BookElement element) {
        return element.getPrice();
    }
}

interface Element {
    Object accept(Visitor visitor);
}

class AppleElement implements Element {
    private Date expireDate;
    private int price;

    public AppleElement(Date expireDate, int price) {
        this.expireDate = expireDate;
        this.price = price;
    }

    public boolean isPerfect() {
        return true;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public int getPrice() {
        return price;
    }

    public Object accept(Visitor visitor) {
        return visitor.visitAppleElement(this);
    }

    public String toString() {
        return "AppleElement{Date=" + 
            String.format("%1$tF %1$tT", expireDate) + 
            ", price=" + price + "}";
    }
}

class BookElement implements Element {
    private int price;

    public BookElement(int price) {
        this.price = price;
    }

    public boolean isPerfect() {
        return true;
    }

    public int getPrice() {
        return price;
    }

    public Object accept(Visitor visitor) {
        return visitor.visitBookElement(this);
    }

    public String toString() {
        return "BookElement{price=" + price + "}";
    }
}

class ObjectStructure {
    private List<Element> elements = new ArrayList<Element>();

    public void addElement(Element element) {
        elements.add(element);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }

    public Iterator<Element> iterator() {
        return elements.iterator();
    }
}

class VisitorPattern {
    public static void main(String[] args) {
        ObjectStructure objectStructure = new ObjectStructure();

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(2017, 11-1, 1, 0, 0, 0);
        Date date = gregorianCalendar.getTime();
        Element appleElement = new AppleElement(date, 1000);
        Element bookElement = new BookElement(1500);
        objectStructure.addElement(appleElement);
        objectStructure.addElement(bookElement);

        Visitor consumer = new Consumer();
        Visitor cashier = new Cashier();

        Iterator<Element> elementIterator = objectStructure.iterator();
        Element element;
        while (elementIterator.hasNext()) {
            if (!(Boolean) (element = elementIterator.next()).accept(consumer)) {
                elementIterator.remove();
                System.out.println(element + " is not satisfied");
            }
        }

        int sum = 0;
        elementIterator = objectStructure.iterator();
        while (elementIterator.hasNext())
            sum += (Integer)(elementIterator.next().accept(cashier));
        System.out.println("sum = " + sum);
    }
}

