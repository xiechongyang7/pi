package com.seesea.pi.base.exception;


/**
* @description 业务异常
* @since JDK1.8
* @createTime 2020/5/6 20:49
* @author xie
*/
public class BizException extends BaseException{

    public BizException(){
        super();
    }

    public BizException(String errCode,String errMsg){
        super(errCode,errMsg);
        this.errCode = errCode;
    }

    public BizException(String errCode,String errMsg,Object... args){
        super(errCode,String.format(errMsg.contains("%s")?errMsg:errMsg+"%s",args==null||"".equals(args)?" ":args));
        this.errCode = errCode;
    }

}
