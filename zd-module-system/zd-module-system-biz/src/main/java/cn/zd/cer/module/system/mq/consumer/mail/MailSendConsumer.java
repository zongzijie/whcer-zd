package cn.zd.cer.module.system.mq.consumer.mail;

import cn.zd.cer.framework.mq.core.stream.AbstractStreamMessageListener;
import cn.zd.cer.module.system.mq.message.mail.MailSendMessage;
import cn.zd.cer.module.system.mq.message.sms.SmsSendMessage;
import cn.zd.cer.module.system.service.mail.MailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link MailSendMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class MailSendConsumer extends AbstractStreamMessageListener<MailSendMessage> {

    @Resource
    private MailSendService mailSendService;

    @Override
    public void onMessage(MailSendMessage message) {
        log.info("[onMessage][消息内容({})]", message);
        mailSendService.doSendMail(message);
    }

}
