package com.dexingworld.hanfu.middleware.mail.component;

import com.dexingworld.hanfu.middleware.mail.bean.EmailAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.Iterator;
import java.util.List;

/**
 * 邮件发送类
 * Created by wangpeng on 2016/11/28.
 */
@Component
public class MailSender{

    @Autowired
    @Qualifier("javaMailSender")
    private JavaMailSender javaMailSender;

    /**
     * 邮件发送（文本）
     * @param subject
     * @param toEmail
     * @param fromEmail
     * @param text
     */
    public void send(final String subject, final String toEmail, final String fromEmail, final String text) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
                message.setSubject(subject);
                message.setTo(toEmail);
                message.setFrom(fromEmail);
                message.setText(text, true);
            }
        };
        this.javaMailSender.send(preparator);
    }


    /**
     * 邮件发送，包含附件
     * @param subject
     * @param toEmail
     * @param fromEmail
     * @param text
     * @param attachments
     */
    public void send(final String subject, final String toEmail, final String fromEmail, final String text, final List<EmailAttachment> attachments) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
                message.setSubject(subject);
                message.setTo(toEmail);
                message.setFrom(fromEmail);
                message.setText(text, true);
                if(attachments != null && attachments.size() > 0) {
                    Iterator iterator = attachments.iterator();
                    while(iterator.hasNext()) {
                        EmailAttachment emailAttachment = (EmailAttachment)iterator.next();
                        message.addAttachment(emailAttachment.getAttachmentName(), emailAttachment.getAttachmentFile());
                    }
                }

            }
        };
        this.javaMailSender.send(preparator);
    }


    /**
     * 邮件发送，自定义格式
     * @param preparator
     */
    public void send(MimeMessagePreparator preparator) {
        if(preparator != null) {
            this.javaMailSender.send(preparator);
        }

    }
}
