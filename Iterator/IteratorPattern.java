import java.util.*;

interface Iterator<T> {
    boolean hasNext();
    T next();
    T remove();
    void rewind();
}

// 外禀迭代子 开始

class ListIterator<T> implements Iterator<T> {
    private MyList<T> list;
    private int cursor = 0;

    public ListIterator(MyList<T> list) {
        this.list = list;
    }

    public T next() {
        if (list == null || cursor >= list.size())
            throw new RuntimeException("list == null " + 
                "|| cursor >= list.size()");
        return list.get(cursor++);
    }

    public boolean hasNext() {
        return list != null && cursor < list.size();
    }

    public void rewind() {
        cursor = 0;
    }

    public T remove() {
        if (list == null || cursor > list.size())
            throw new RuntimeException("list == null " + 
                "|| cursor >= list.size()");
        return list.remove(--cursor);
    }
}

interface MyList<T> {
    void add(T element);
    T get(int index);
    T remove(int index); 
    int size();
    String toString();
    Iterator<T> iterator();
}

class MyListImpl<T> implements MyList<T> {
    private List<T> innerList = new ArrayList<>();

    public void add(T element) {
        innerList.add(element);
    }

    public T get(int index) {
        return innerList.get(index);
    }

    public T remove(int index) {
        return innerList.remove(index);
    }

    public int size() {
        return innerList.size();
    }

    public String toString() {
        return "MyListImpl{innerList=" + innerList + "}";
    }

    public Iterator<T> iterator() {
        return new ListIterator<T>(this);
    }
}

// 外禀迭代子 结束


// 内禀迭代子 开始

interface MyList2<T> {
    void add(T element);
    Iterator<T> iterator();
}

class MyList2Impl<T> implements MyList2<T> {
    private List<T> innerList = new ArrayList<>();
    
    public void add(T element) {
        innerList.add(element);
    }

    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    public String toString() {
        return "MyList2Impl{innerList=" + innerList + "}";
    }

    private class InnerIterator implements Iterator<T> {
        private int cursor = 0;

        public boolean hasNext() {
            return cursor < innerList.size();
        }

        public T next() {
            return innerList.get(cursor++);
        }

        public T remove() {
            return innerList.remove(--cursor);
        }

        public void rewind() {
            cursor = 0;
        }
    }
}

// 内禀迭代子 结束


public class IteratorPattern {
    public static void main(String[] args) {
        MyList<Integer> list = new MyListImpl();
        list.add(1); list.add(2); list.add(3);
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(list);

        System.out.println("==========");

        MyList2<Integer> list2 = new MyList2Impl();
        list2.add(1); list2.add(2); list2.add(3);
        iterator = list2.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(list2);
    }
}

