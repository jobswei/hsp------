package QQClient.common;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class test1 {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        // oos.writeObject(u); // 发送user对象
            // oos.flush();

        Message ms_user=new Message();
        ms_user.setContent("12"+" "+"22");
        oos.writeObject(ms_user);
        oos.flush();

    }
}
