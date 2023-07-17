package QQServer.service;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import QQClient.common.Message;
import QQClient.common.MessageType;

public class ServerClientThread extends Thread {
    private Socket socket;
    private String userId;
    public ServerClientThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run(){
        while(true){
            try {
                System.out.println("Server and Client "+userId+" connected successfully");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message ms = (Message)ois.readObject();
                if(ms.getMesType().equals(MessageType.MESSAGE_GET_ONLINE_FRIEND)){
                    System.out.println(ms.getSender()+" want to get online users");
                    String onlineUser=ManageClientThreads.getOnlineUser();
                    Message ms2=new Message();
                    ms2.setMesType(MessageType.MESSAGE_RET_ONLINE_FREIND);
                    ms2.setContent(onlineUser);
                    ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(ms2);
                    oos.flush();
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            
        }
    }
    
}
