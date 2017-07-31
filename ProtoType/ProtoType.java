import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ProtoType implements Cloneable {
    /*
     *  clone() 实现的是浅拷贝
     */
    @Override
    public ProtoType clone() {
        ProtoType protoType = null;
        try {
            protoType = (ProtoType) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        return protoType;
    }

    /*
     * 通过序列化，实现深拷贝
     */ 
    public ProtoType deepCopy() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (ProtoType) ois.readObject();
    }

    public static void main(String[] args) throws Throwable {
        // 之所以不直接new一个对象，是因为clone方法是native方法，
        // + 直接操作内存中的二进制流，所以效率高
        ConcreteProtoType concreteProtoType = new ConcreteProtoType();
        ConcreteProtoType tmp = (ConcreteProtoType) concreteProtoType.clone();
        concreteProtoType.name = "another ConcreteProtoType";
        concreteProtoType.strings.remove(1);
        tmp.say();

        concreteProtoType = new ConcreteProtoType();
        tmp = (ConcreteProtoType) concreteProtoType.deepCopy();
        concreteProtoType.strings.remove(1);
        tmp.say();
    }
}

class ConcreteProtoType extends ProtoType implements Serializable {
    public String name = "concreteProtoType";
    public List<String> strings = new ArrayList<String>();

    {
        strings.add("hello");
        strings.add("world");
    }

    public void say() {
        System.out.println("name=" + name + ", strings=" + strings);
    }
}

