package com.seesea.pi.base.exception;


/**
* @description
* @since JDK1.8
* @createTime 2020/5/6 20:43
* @author xie
*/
public abstract class BaseException extends Exception{

    public String errCode;

    public BaseException(){
        super();
    }

    public BaseException(String errMsg){
        super(errMsg);
    }

    public BaseException(String errCode,String errMsg){
        super(errMsg);
        this.errCode = errCode;
    }

}
