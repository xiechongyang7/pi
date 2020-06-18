package com.seesea.pi.service;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/6/18 16:47
 */
public interface MailService {

    /**
     *
     * @param mailInfo
     * @param type 定制参数
     */
    void sendMail(String mailInfo,String type);
}
