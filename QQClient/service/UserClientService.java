package QQClient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import QQClient.common.Message;
import QQClient.common.User;
// 该类完成用户登陆验证，注册等功能
import QQServer.common.MessageType;

public class UserClientService {
    // 因为可能在其他地方使用user信息，因此可以作成成员属性
    private User u = new User();
    // socket 在其他地方也可能使用，所以要做成属性
    private Socket socket;

    public boolean checkUser(String userId, String passwd)    {
        boolean b=false;
        u.setUserId(userId);
        u.setPasswd(passwd);

        // 连接服务器，发送u对象
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u); // 发送user对象
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms=(Message)ois.readObject();
            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                b=true;
                // 创建一个和服务器端保持通信的线程 -> 创建一个类 ClientConnectServerThread
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
                // 启动客户端的线程
                ccst.start();
                // 这里为了客户端的扩展，将线程放入集合中管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, ccst);
                
            }else{
                // 如果验证不成功，就无法进行后续操作，关闭socket
                socket.close();
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        return b;

    }
}
