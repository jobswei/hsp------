package QQServer.service;

import java.io.ObjectInputStream;
import java.net.Socket;
import QQClient.common.Message;

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
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    
}
