package QQClient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import QQClient.common.Message;
public class ClientConnectServerThread extends Thread{
    // 该线程要持有socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        // 因为Thread需要在后台和服务器通信，因此做成无限循环
        while(true){
            try {
                System.out.println("Wait for reading message from server");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();

                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    

    
}
