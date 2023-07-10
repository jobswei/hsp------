package QQServer.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import QQClient.common.Message;
import QQClient.common.MessageType;
import QQClient.common.User;

// 这事服务端，监听9999端口，等待客户端的连接并保持通讯
public class QQServer {
    private ServerSocket ss ;

    public QQServer(){
        try {
            System.out.println("服务器在9999端口监听....");
            // 端口可以写在配置文件中
            ss= new ServerSocket(9999);

            while(true){ // 当与某个客户端取得连接后，继续监听
                Socket socket=ss.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user= (User)ois.readObject();
                // 创建mseeage，准备回复
                Message ms= new Message();
                // 验证
                if(user.getUserId().equals("100") && user.getPasswd().equals("123456")){
                    ms.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(ms);
                    oos.flush();
                    // 创建对应于客户端的线程
                }else{
                    ms.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                }

                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
