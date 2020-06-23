package com.seesea.pi.controller;


import com.seesea.pi.base.BaseController;
import com.seesea.pi.common.Const;
import com.seesea.pi.service.ChangeService;
import com.seesea.pi.task.NoticeSshJob;
import com.seesea.pi.task.NoticeWebJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
* @description
* @since JDK1.8
* @createTime 2020/5/6 21:32
* @author xie
*/
@Controller
public class TestController extends BaseController {

    @Autowired
    private NoticeWebJob noticeWebJob;
    @Autowired
    private NoticeSshJob noticeSshJob;
    @Autowired
    private ChangeService changeService;


    @RequestMapping("/index")
    public String getIndex(HttpServletRequest request){
//        request.setAttribute("result","success");
        return "/index.html";
    }

    @RequestMapping("/web/task")
    public String webTask(HttpServletRequest request){
        try {
            noticeWebJob.doTask();
            request.setAttribute("result","success");
        }catch (Exception e){
            request.setAttribute("result",e.getMessage());
        }
        return "/index.html";
    }

    @RequestMapping("/ssh/task")
    public String sshTask(HttpServletRequest request){

        try {
            noticeSshJob.doTask();
            request.setAttribute("result","success");
        }catch (Exception e){
            request.setAttribute("result",e.getMessage());
        }

        return "/index.html";
    }

    @RequestMapping("/ssh/change")
    public String changeSsh(HttpServletRequest request){

        try{
            changeService.change(Const.change_tpye_ssh);
            request.setAttribute("result","success");
        }catch (Exception e){
            request.setAttribute("result",e.getMessage());

        }

        return "/index.html";
    }

    @RequestMapping("/sql/change")
    public String changeChange(HttpServletRequest request){

        try{
            changeService.change(Const.change_tpye_sql);
            request.setAttribute("result","success");
        }catch (Exception e){
            request.setAttribute("result",e.getMessage());

        }

        return "/index.html";
    }
}


