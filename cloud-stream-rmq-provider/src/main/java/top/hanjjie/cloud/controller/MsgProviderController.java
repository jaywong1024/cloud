package top.hanjjie.cloud.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hanjjie.cloud.exception.ParamsException;
import top.hanjjie.cloud.tools.MsgSender;
import top.hanjjie.cloud.utils.ResultBean;

import javax.annotation.Resource;

@RestController
@RequestMapping("/msg")
public class MsgProviderController {

    @Resource
    private MsgSender msgSender;

    @GetMapping("/send")
    public ResultBean<?> send(String msg) {
        if (StringUtils.isBlank(msg)) throw new ParamsException("msg is required");
        msgSender.send(msg);
        return new ResultBean<>();
    }

}
