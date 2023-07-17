package QQClient.common;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class test2 {
    public static void main(String[] args) throws Exception {
        ServerSocket ss=new ServerSocket(9999);
        Socket socket=ss.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


        // 此处有一个bug尚未修复 用Object接收user不会出错，强制转化为User时会报错
        // User user= (User)ois.readObject();
        Object ms_user=ois.readObject();
        // User user=new User(ms_user.getContent().split(" ")[0],ms_user.getContent().split(" ")[1]);


        System.out.println("OK");
    }
}
