import java.util.*;

interface User {
    void sendMessage(String message, 
        List<String> toUserNames);
    void receiveMessage(String fromUserName, String message);
    String getName();
}

class UserImpl implements User {
    private String name;
    private ChatRoom chatRoom;

    public UserImpl(String name, ChatRoom chatRoom) {
        this.name = name;
        this.chatRoom = chatRoom;
        this.chatRoom.addUser(this);
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message,
        List<String> toUserNames) {
        chatRoom.sendMessage(this, message, toUserNames);
    }

    public void receiveMessage(String fromUserName, String message) {
        System.out.println("[" + name + " receives a message from " 
            + fromUserName + "]: " + message);
    }
}

interface ChatRoom {
    void sendMessage(User fromUser,
        String message, List<String> toUserNames);
    void addUser(User user);
    void removeUser(String userName);
}

class ChatRoomImpl implements ChatRoom {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    public void removeUser(String userName) {
        if (users.containsKey(userName))
            users.remove(userName);
    }

    public void sendMessage(User fromUser,
        String message, List<String> toUserNames) {
        if (toUserNames == null) {
            for (Map.Entry<String, User> entry: users.entrySet()) {
                entry.getValue().receiveMessage(fromUser.getName(),
                    message);
            }
            return;
        }

        for (String toUserName: toUserNames) {
            if (users.containsKey(toUserName))
                users.get(toUserName).receiveMessage(fromUser.getName(), message);
        }
    }
}


class MediatorPattern {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoomImpl();

        User[] users = new User[10];
        for (int i = 0; i < users.length; i++) {
            users[i] = new UserImpl("User"+i, chatRoom);
        }

        users[0].sendMessage("hello everyone", null);
        users[2].sendMessage("hi, User3", Arrays.asList("User3"));
        users[3].sendMessage("hi, User2", Arrays.asList("User2"));
    }
}

