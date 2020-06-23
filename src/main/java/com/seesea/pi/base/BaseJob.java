package com.seesea.pi.base;


import com.seesea.pi.base.exception.BizException;

import java.io.IOException;

/**
* @description 任务基类
* @since JDK1.8
* @createTime 2020/5/6 20:31
* @author xie
*/
public abstract class BaseJob extends BaseLogger{

    public abstract void doTask() throws IOException, BizException;

}
