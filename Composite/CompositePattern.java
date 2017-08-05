import java.util.*;

interface Node {
    boolean isComposite();
    String getName();
    Node getNodeByName(String name);
    int addNode(Node node);
    int ls();
    void tree();
    Map<String, Node> getSubNode();
}

class File implements Node {
    private String name;

    public boolean isComposite() {
        return false;
    }

    public File(String name) {
        if (name == null)
            throw new RuntimeException("name == null");
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public int addNode(Node node) {
        System.out.println(name + " is not a directory");
        return 1;
    }

    public Node getNodeByName(String name) {
        throw new RuntimeException(name + " is not a directory");
    }

    public Map<String, Node> getSubNode() {
        throw new RuntimeException(name + " is not a directory");
    }

    public int ls() {
        System.out.println(name + " is not a directory");
        return 1;
    }

    public void tree() {
        System.out.println(name);
    }
}

class Directory implements Node {
    private String name;
    private Map<String, Node> map = new HashMap<>();

    public boolean isComposite() {
        return true;
    }

    public Directory(String name) {
        if (name == null)
            throw new RuntimeException("name == null");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int addNode(Node node) {
        if (node == null) {
            System.out.println("node == null");
            return 2;
        }
        if (map.containsKey(node.getName())) {
            System.out.println(node.getName() + " already exists");
            return 3;
        }
        map.put(node.getName(), node);
        return 0;
    }

    public int ls() {
        for (Map.Entry<String, Node> entry: map.entrySet()) {
            System.out.println(entry.getKey());
        }
        return 0;
    }

    public Node getNodeByName(String name) {
        if (name == null || !map.containsKey(name))
            throw new RuntimeException("name not exists");
        return map.get(name);
    }

    public void tree() {
        tree("", this);
    }

    public static String repeat(String string, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; ++i)
            sb.append(string);
        return sb.toString();
    }

    public Map<String, Node> getSubNode() {
        return map;
    }

    private static void tree(String prefix, Node node) {
        System.out.println(prefix + node.getName());
        if (!node.isComposite())
            return;
        for (Map.Entry<String, Node> entry: node.getSubNode().entrySet()) {
            String newPrefix = prefix + repeat(
                " ", 
                node.getName().length() + 1);
            tree(newPrefix, entry.getValue());
        }
    }
}

public class CompositePattern {
    public static void main(String[] args) {
        Node root = new Directory("/");
        root.addNode(new Directory("etc"));
        root.addNode(new Directory("lib"));
        root.getNodeByName("lib").addNode(new File("ld.so"));
        Node etc = root.getNodeByName("etc");
        etc.addNode(new File("my.cnf"));
        Node myCnf = etc.getNodeByName("my.cnf");
        myCnf.tree();
        root.tree();
    }
}

