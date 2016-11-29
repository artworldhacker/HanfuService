package com.dexingworld.hanfu.middleware.mail.test;

import com.dexingworld.hanfu.middleware.mail.bean.EmailAttachment;
import com.dexingworld.hanfu.middleware.mail.component.MailSender;
import com.dexingworld.hanfu.test.BaseTest;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * Created by wangpeng on 2016/11/28.
 */

public class MailSenderTest extends BaseTest {

    @Autowired
    private MailSender mailSender;

    @Test
    public void send(){
        mailSender.send("测试邮件发送","562317154@qq.com","wonpera_562@163.com","Hello World!!!!!!");
    }


    @Test
    public void sendWithFiles(){
        String foUser = "562317154@qq.com";
        String fromUser = "wonpera_562@163.com";
        List<EmailAttachment> lists = Lists.newArrayList();
        lists.add(new EmailAttachment(new File("F:\\git\\HanfuService\\hanfu-middleware\\hanfu-mail\\src\\main\\java\\com\\dexingworld\\hanfu\\middleware\\mail\\config\\MailConfigration.java")));
        String content = "附件为百度api，请注意查收!!!";
        mailSender.send("测试邮件发送带附件",foUser,fromUser,content,lists);
    }
}
