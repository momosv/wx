package com.cn.xt.mp.mobile;

import com.cn.xt.mp.base.baseController.BaseController;
import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.entity.Tips;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.base.util.DatePattern;
import com.cn.xt.mp.base.util.RandomUtils;
import com.cn.xt.mp.base.util.XDateUtils;
import com.cn.xt.mp.mpModel.TbFeedback;
import com.cn.xt.mp.service.ICompanyUserService;
import com.cn.xt.mp.service.impl.CompanyUserServiceImpl;
import com.cn.xt.mp.vo.CompanyUserVO;
import com.cn.xt.mp.wxSecurity.service.TempMaterialService;
import com.cn.xt.mp.wxSecurity.wxentity.message.TemplateData;
import com.cn.xt.mp.wxSecurity.wxentity.message.WeChatTemplate;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 15:40
 **/
@Api(value = "MpCompanyCtrl",tags = "企业用户操作",description = "反馈信息，企业/个人信息设置")
@RequestMapping("company")
@RestController
public class MpCompanyCtrl extends BaseController {


    @Autowired
    public TempMaterialService tempMaterialService;

    @Autowired
    public ICompanyUserService companyUserService;


    @ApiOperation(value = "企业用户提交反馈单",notes = "获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="标题",dataType="string", paramType = "query",example="被乱罚款了",required = true),
            @ApiImplicitParam(name="content",value="内容",dataType="string", paramType = "query",example="如题",required = true),
    })
    @PostMapping(value = "addFeedback")
    public Msg addFeedback(TbFeedback feedback) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
       if(StringUtils.isEmpty(feedback.getTitle())||StringUtils.isEmpty(feedback.getContent())){
           return  Msg.fail(Tips.FEEBACK_MAIN_NULL.getCode(),Tips.FEEBACK_MAIN_NULL.getDesc());
       }
       feedback.setCode(RandomUtils.getDateWithRan());
       feedback.setId(RandomUtils.getUUID32());
       feedback.setCreateType(Constants.FEEDBACK_COMPANY_USER_CREATE);
       feedback.setCreator(userVO.getOpenid());
       feedback.setCompanyId(userVO.getCompanyId());
       feedback.setCreateTime(new Date());
       companyUserService.addFeedback(feedback,userVO);
        return Msg.success("保存成功");
    }

    @RequestMapping("getFeedback/{id}")
    public Msg getFeedback(@PathVariable String id) throws Exception {
        CompanyUserVO userVO = getCompanyUser();





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
