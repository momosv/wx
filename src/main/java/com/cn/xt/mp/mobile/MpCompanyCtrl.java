package com.cn.xt.mp.mobile;

import com.cn.xt.mp.base.entity.Msg;
import org.springframework.web.bind.annotation.RequestMapping;
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



    public Msg addFeedback(){

        return Msg.success();
    }

}
