package top.hanjjie.cloud.tools.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import top.hanjjie.cloud.tools.MsgSender;

import javax.annotation.Resource;

@Slf4j
@EnableBinding(Source.class) // 定义消息推送管道
public class MsgSenderImpl implements MsgSender {

    @Resource
    private MessageChannel output;

    @Override
    public void send(String msg) {
        log.info("发送消息：{}", msg);
        output.send(MessageBuilder.withPayload(msg).build());
    }
}
