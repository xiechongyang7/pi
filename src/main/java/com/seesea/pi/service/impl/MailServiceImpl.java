package com.seesea.pi.service.impl;

import com.seesea.pi.base.BaseService;
import com.seesea.pi.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/6/18 16:47
 */
@Service
public class MailServiceImpl extends BaseService implements MailService {

    @Value("spring.mail.username")
    private String mailUsername;

    @Value("target.mail")
    private String targetMail;


    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendMail(String mailInfo,String type){
        MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());//true表示支持复杂类型
        try {
//            messageHelper.setFrom("791465939@qq.com");
            messageHelper.setFrom(mailUsername);
            messageHelper.setText(mailInfo);//邮件内容
//            messageHelper.setTo("xiechongyang7@163.com");
            messageHelper.setTo(targetMail);
//            messageHelper.setTo("791465939@qq.com");
            messageHelper.setSubject("树莓派地址变更=="+type+"==");
            messageHelper.setSentDate(new Date());
            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败",e);
        }

    }


}
