package com.seesea.pi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/6/18 16:55
 */
public class ShellUtil {

    static Logger logger = LoggerFactory.getLogger(ShellUtil.class);
    public static void runShell(String shellPath) {


        String cmd = "./"+shellPath;

        logger.info("开始执行脚本:" + cmd);
        Process process = null;
        BufferedReader br = null;

        try {
            process = Runtime.getRuntime().exec(cmd);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String str = null;

            while ((str = br.readLine()) != null) {
                logger.info("脚本执行" + str);
                if ((str != null) && (!str.equals(""))) {
                    if (str.indexOf("SOME_ERR_KEYWORD") > 0) {
                        logger.error("脚本执行错误:" + str);
                        return;
                    }
                }
            }
            br.close();
            process.waitFor();

            logger.info("脚本:" + cmd + "执行正常结束");

        } catch (Exception e) {
            logger.error("脚本执行错误", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
            if (process != null) {
                process.destroy();
            }
        }
    }
}
