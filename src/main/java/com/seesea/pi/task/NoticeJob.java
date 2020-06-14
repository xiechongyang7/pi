package com.seesea.pi.task;


import com.seesea.pi.base.BaseJob;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
* @description
* @since JDK1.8
* @createTime 2020/5/7 20:06
* @author xie
*/
@Component
public class NoticeJob extends BaseJob {


    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${file.path.log.web}")
    private String webLogFilePath;

    @Value("${file.path.log.ssh}")
    private String sshLogFilePath;

    @Override
    @Scheduled(cron = "${job.log.cron}")
    public void doTask() {



        try {

            check(webLogFilePath,"WEB");
            check(sshLogFilePath,"SSH");

        } catch (Exception e) {
            logger.error("隧道日志文件处理错误",e);
        }

    }


    private void check(String path,String type) throws IOException {
        StringBuilder builder = new StringBuilder();
        File webLogfile = new File(path);
        List<String> logStrList = FileUtils.readLines(webLogfile);


        if(null != logStrList && logStrList.size() != 0){
            for(String str : logStrList){
                builder.append(str).append("\n");
            }
            // 发送邮件
            sendMail(builder.toString(),type);
        }


        FileUtils.writeStringToFile(webLogfile,"",false);

    }


    public void sendMail(String mailInfo,String type){
        MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());//true表示支持复杂类型
        try {
            messageHelper.setFrom("791465939@qq.com");
            messageHelper.setText(mailInfo);//邮件内容
            messageHelper.setTo("xiechongyang7@163.com");
//            messageHelper.setTo("791465939@qq.com");
            messageHelper.setSubject("树莓派地址变更=="+type+"==");
            messageHelper.setSentDate(new Date());
            javaMailSender.send(messageHelper.getMimeMessage());
        } catch (MessagingException e) {
            logger.error("发送邮件失败",e);
        }

    }

}
