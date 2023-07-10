package QQServer.service;
import java.util.HashMap;

public class ManageClientThreads {
    private static HashMap<String, ServerClientThread> hm;

    public static void addServerClientThread(ServerClientThread sct, String userId){
        hm.put(userId, sct);
    }

    public static ServerClientThread getServerClientThread(String userId){
        return hm.get(userId);
    }
    
}
