package com.seesea.pi.base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @description
* @since JDK1.8
* @createTime 2020/5/6 20:31
* @author xie
*/
public abstract class BaseLogger {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

}
