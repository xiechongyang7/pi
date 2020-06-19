package com.seesea.pi.task;


import com.seesea.pi.base.BaseJob;
import com.seesea.pi.service.MailService;
import com.seesea.pi.util.ShellUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
public class NoticeSshJob extends BaseJob {


    @Autowired
    private MailService mailService;

    @Value("${file.path.log.ssh}")
    private String sshLogFilePath;


    @Override
    @Scheduled(cron = "${job.notice.cron}")
    public void doTask() {

        try {
            logger.info("SSH日志文件检测开始" + new Date());
            check(sshLogFilePath,"SSH");
            logger.info("SSH日志文件检测结束");
        } catch (Exception e) {
            logger.error("隧道日志文件处理错误",e);
        }

    }


    private void check(String path,String type) throws IOException {
        StringBuilder builder = new StringBuilder();
        File sshLogfile = new File(path);
        List<String> logStrList = FileUtils.readLines(sshLogfile);

        if(null != logStrList && logStrList.size() != 0  && sshLogfile.length()> 10){
            for(String str : logStrList){
                if(str.contains("Tunnel established")){
                    //e.g. [06/11/20 15:17:20] [INFO] [client] Tunnel established at http://kwrs4y.natappfree.cc
                    builder.append(str).append("\n");
                }
            }

            // 发送邮件
            mailService.sendMail(builder.toString(),type);
            // 清空文件
            FileUtils.writeStringToFile(sshLogfile,"",false);
        }
    }






}
