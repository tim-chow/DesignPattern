import java.util.*;

class Snapshot {
    private String content;

    public Snapshot(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class Caretaker {
    private static List<Snapshot> queue = 
        new LinkedList<>();
    private static int maxSize = 10;

    public static void setMaxSize(int maxSize) {
        Caretaker.maxSize = maxSize;
    }

    public static void addSnapshot(Snapshot snapshot) {
        if (queue.size() >= maxSize)
            queue.remove(0);
        queue.add(snapshot);
    }

    public static Snapshot getSnapshot(int offset) {
        if (offset >= queue.size())
            return null;
        return queue.get(offset);
    }
}

class Editor {
    private String content;

    public Editor(String content) {
        this.content = content;
    }
    
    public Snapshot createSnapshot() {
        return new Snapshot(content);
    }

    public void recoverFromSnapshot(Snapshot snapshot) {
        content = snapshot.getContent();
    }

    public void changeContent(int startOffset,
        int endOffset, String newContent) {
        StringBuilder sb = new StringBuilder();
        sb.append(content.substring(0, startOffset));
        sb.append(newContent);
        sb.append(content.substring(endOffset+1));
        content = sb.toString();
    }

    public String getContent() {
        return content;
    }
}

class MementoPattern {
    public static void main(String[] args) {
        Editor editor = new Editor("this is a original content");
        System.out.println(editor.getContent());
        Snapshot snapshot = editor.createSnapshot();
        Caretaker.addSnapshot(snapshot);

        editor.changeContent(8, 17, "another");
        System.out.println(editor.getContent());
        snapshot = editor.createSnapshot();
        Caretaker.addSnapshot(snapshot);

        editor.recoverFromSnapshot(Caretaker.getSnapshot(1));
        System.out.println(editor.getContent());
        editor.recoverFromSnapshot(Caretaker.getSnapshot(0));
        System.out.println(editor.getContent());
    }
}

