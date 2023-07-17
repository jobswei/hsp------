package QQServer.service;
import java.util.HashMap;
import java.util.Iterator;

public class ManageClientThreads {
    private static HashMap<String, ServerClientThread> hm;
    
    public static void addServerClientThread(ServerClientThread sct, String userId){
        hm.put(userId, sct);
    }

    public static ServerClientThread getServerClientThread(String userId){
        return hm.get(userId);
    }

    // 返回在线用户列表的方法
    public static String getOnlineUser(){
        // 集合遍历， 遍历hashmap的key
        Iterator<String> iterator=hm.keySet().iterator();
        String onlineUserList="";
        while(iterator.hasNext()){
            onlineUserList+=iterator.next().toString()+" ";
        }
        return onlineUserList;
    }
    
}
