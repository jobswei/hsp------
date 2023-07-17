package QQServer.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import QQServer.common.Message;
import QQServer.common.MessageType;
import QQServer.common.User;

// 这事服务端，监听9999端口，等待客户端的连接并保持通讯
public class QQServer {
    private ServerSocket ss ;
    // 创建一个集合，存放多个用户，如果这些用户登录，则认为是合法的
    private static HashMap<String, User> vaildUsers = new HashMap<>();

    static{ // 在静态代码块 初始化
        vaildUsers.put("100",new User("100","123456"));
        vaildUsers.put("200",new User("100","123456"));
        vaildUsers.put("300",new User("100","123456"));

    }

    // 验证用户是否有效的方法
    private boolean checkUser(String userId, String passwd){
        User user=vaildUsers.get(userId);
        if(user == null){
            return false;
        }
        if(!user.getPasswd().equals(passwd)){
            return false;
        }
        return true;
    }

    public QQServer(){
        try {
            System.out.println("Server is listening at port 9999....");
            // 端口可以写在配置文件中
            ss= new ServerSocket(9999);

            while(true){ // 当与某个客户端取得连接后，继续监听
                Socket socket=ss.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());


                // 此处有一个bug尚未修复 用Object接收user不会出错，强制转化为User时会报错
                // User user= (User)ois.readObject();
                Message ms_user=(Message)ois.readObject();
                User user=new User(ms_user.getContent().split(" ")[0],ms_user.getContent().split(" ")[1]);


                System.out.println("OK");
                // 创建mseeage，准备回复
                Message ms= new Message();
                System.out.println("OK");
                // 验证
                if(checkUser(user.getUserId(), user.getPasswd())){
                    ms.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(ms);
                    oos.flush();
                    // 创建对应于客户端的线程
                    ServerClientThread sct = new ServerClientThread(socket, user.getUserId());
                    sct.start();
                    // 放入集合中进行管理
                    ManageClientThreads.addServerClientThread(sct, user.getUserId());
                    
                }else{
                    System.out.println("User "+user.getUserId()+" login failed");
                    ms.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(ms);
                    oos.flush();
                    oos.close();
                    socket.close();
                }

                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            // 如果服务端退出了while循环，就说明不再监听了，我们需要关闭资源
            try {
                ss.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
