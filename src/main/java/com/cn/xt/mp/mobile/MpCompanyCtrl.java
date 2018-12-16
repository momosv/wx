package com.cn.xt.mp.mobile;

import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.mpModel.TbFeedback;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 15:40
 **/
@RequestMapping("mp/company")
@RestController
public class MpCompanyCtrl {

    @RequestMapping("addFeedback")
    public Msg addFeedback(TbFeedback feeback){
        return Msg.success();
    }

    @RequestMapping("getFeedback/{id}")
    public Msg getFeedback(@PathVariable String id){
        return Msg.success();
    }

    @RequestMapping("updateFeedback/{id}")
    public Msg updateFeedback(TbFeedback feeback){
        return Msg.success();
    }

    @RequestMapping("deleteFeedback/{id}")
    public Msg deleteFeedback(TbFeedback feeback){
        return Msg.success();
    }

    @RequestMapping("getFeedbackList")
    public Msg getFeedbackList(@RequestParam(required = false,defaultValue = "") String title,
                               @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                               @RequestParam(name="pageSize",defaultValue = "10")int pageSize){

        Page page = PageHelper.startPage(pageNum, pageSize);
        //sql
        PageInfo pageInfo =  new PageInfo(page.getResult());
        return Msg.success().add("page",pageInfo);
    }

    @RequestMapping("evaluateFeedback/{id}")
    public Msg evaluateFeedback(TbFeedback feeback){
        return Msg.success();
    }

}
