//package com.seesea.pi.task;
//
//
//import com.seesea.pi.base.BaseJob;
//import org.apache.commons.io.FileUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
///**
//* @description 删除日志
//* @since JDK1.8
//* @createTime 2020/5/6 20:31
//* @author xie
//*/
//@Component
//public class WebLogTask extends BaseJob {
//
//
//    @Value("${file.path.log.web}")
//    private String webLogFilePath;
//
//    @Value("${file.path.log,info}")
//    private String infoLogFilePath;
//
//    @Override
//    @Scheduled(cron = "${job.log.cron}")
//    public void doTask() {
//
//        StringBuilder builder = new StringBuilder("");
//
//        try {
//            File infoFile = new File(infoLogFilePath);
//            File webLogfile = new File(webLogFilePath);
//            List<String> logStrList = FileUtils.readLines(webLogfile);
//
//            for (String str : logStrList){
//                if(str.contains("Url")||str.contains("INFO")){
//                    builder.append("-----web------").append(str).append("/n");
//                }
//            }
//
//            FileUtils.writeStringToFile(infoFile,builder.toString(),true);
//            FileUtils.writeStringToFile(webLogfile,"",false);
//
//        } catch (Exception e) {
//            logger.error("web隧道日志文件处理错误",e);
//        }
//
//    }
//}
