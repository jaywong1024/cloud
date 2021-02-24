package top.hanjjie.cloud.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(Sink.class)
public class MsgListener {

    @StreamListener(Sink.INPUT)
    public void input(Message<String> msg) {
        log.info("接收到消息：{}", msg.getPayload());
    }

}
