package QQClient.common;

public interface MessageType {
    // 1. 在接口中定义了一些常量
    // 2. 不同的常量表示不同的消息
    String MESSAGE_LOGIN_SUCCEED = "1"; // 登陆成功
    String MESSAGE_LOGIN_FAIL = "2"; // 登陆失败
    String MESSAGE_COMM_MES="3";
    String MESSAGE_GET_ONLINE_FRIEND="4";
    String MESSAGE_RET_ONLINE_FREIND="5";
    String MESSAGE_CLIENT_EXIT="6";

}
