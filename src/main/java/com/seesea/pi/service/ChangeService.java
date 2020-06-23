package com.seesea.pi.service;

import com.seesea.pi.base.exception.BizException;

/**
 * @description
 * @author: xcy
 * @createTime: 2020/6/23 14:56
 */
public interface ChangeService {
    void change(String type) throws BizException;
}
