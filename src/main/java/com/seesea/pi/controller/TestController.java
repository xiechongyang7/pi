package com.seesea.pi.controller;


import com.seesea.pi.base.BaseController;
import com.seesea.pi.task.NoticeJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @description
* @since JDK1.8
* @createTime 2020/5/6 21:32
* @author xie
*/
@RestController
public class TestController extends BaseController {

    @Autowired
    private NoticeJob noticeJob;

    @GetMapping("/test")
    public String test(){
        noticeJob.doTask();

        return "---------------\n" +
                "----success----\n" +
                "---------------";

    }
}


