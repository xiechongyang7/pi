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
    private MailService mailService;

    @Value("${file.path.log.web}")
    private String webLogFilePath;

    @Value("shell.path")
    private String shellPath;

    @Value("nginx.config.path")
    private String nginxConfigPath;

    @Override
    @Scheduled(cron = "${job.log.cron}")
    public void doTask() {

        try {

            check(webLogFilePath,"WEB");
//            check(sshLogFilePath,"SSH");

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
                if(str.contains("Tunnel established")){
                    //e.g. [06/11/20 15:17:20] [INFO] [client] Tunnel established at http://kwrs4y.natappfree.cc
                    builder.append(str).append("\n");
                }
            }

            // 发送邮件
            mailService.sendMail(builder.toString(),type);
            // 清空文件
            FileUtils.writeStringToFile(webLogfile,"",false);
            upNgFile(builder.toString());

            // 执行shell 命令
            ShellUtil.runShell(shellPath);
        }
    }



    private void upNgFile(String str){
        String[] strs = str.split(" ");
        String address = strs[strs.length-1];
        File nginxConfigFile = new File(nginxConfigPath);

        try {
            List<String> configStr = FileUtils.readLines(nginxConfigFile);
            StringBuilder builder = new StringBuilder();
            for(int i = 0;i < configStr.size();i++){
                if(i == 6){
                    builder.append("          index "+address+";");
                }else {
                    builder.append(configStr.get(i));
                }
            }
            FileUtils.writeStringToFile(nginxConfigFile,builder.toString(),false);
        } catch (IOException e) {
            logger.error("操作nginx配置文件出错",e);
        }
    }


}
