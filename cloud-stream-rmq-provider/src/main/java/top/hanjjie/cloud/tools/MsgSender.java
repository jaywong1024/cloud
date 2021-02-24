package top.hanjjie.cloud.tools;

/**
 * 消息生产者接口
 */
public interface MsgSender {

    void send(String msg);

}
