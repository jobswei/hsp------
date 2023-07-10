package QQClient.view;

import QQClient.service.UserClientService;
import QQClient.utils.Utility;

public class QQView {
    private boolean loop=true;
    private UserClientService userClientService = new UserClientService(); // 用于登录服务器/注册用户

    public static void main(String[] args){
        new QQView().mainMenu();
        System.out.println("User logout");
    }
    private void mainMenu(){
        while (loop){
            System.out.println("=======Welcom to login net-communicate system===========");
            System.out.println("\t\t1 Login in");
            System.out.println("\t\t9 Login out");
            System.out.print("Please enter your choice: ");
            String key=Utility.readString(1);

            // 根据用户的输入，来处理不同的逻辑
            switch (key){
                case "1":
                    System.out.print("User name:");
                    String userId=Utility.readString(50);
                    System.out.print("Pass word: ");
                    String passwd=Utility.readString(50);
                    // 这里就比较麻烦了，需要到服务端去验证该用户是否合法
                    if(userClientService.checkUser(userId, passwd)){
                        System.out.println("========Welcome "+userId+" =========");
                        while(loop){ // 进入二级菜单
                            System.out.println("\n=========Net Communicate System 2-nd Menu(user: " +userId+")=============");
                            System.out.println("\t\t1 Dispaly all users online");
                            System.out.println("\t\t2 Message to all");
                            System.out.println("\t\t3 Message to one");
                            System.out.println("\t\t4 File Messages");
                            System.out.println("\t\t9 Logout");
                            System.out.print("Enter your choice: ");
                            key=Utility.readString(1);
                            switch(key){
                                case "1":
                                    break;
                                case "2":
                                    break;
                                case "3":
                                    break;
                                case "4":
                                    break;
                                case "9":
                                    loop=false;
                                    break;
                            }
                        }
                    }else{
                        System.out.println("login failed");
                    }
                    break;
                case "9":
                    System.out.println("Login out");
                    loop=false;
                    break;
            }
        }
    }
}
