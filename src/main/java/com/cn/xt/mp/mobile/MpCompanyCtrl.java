package com.cn.xt.mp.mobile;

import com.cn.xt.mp.base.baseController.BaseController;
import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.entity.Tips;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.base.util.DatePattern;
import com.cn.xt.mp.base.util.RandomUtils;
import com.cn.xt.mp.base.util.XDateUtils;
import com.cn.xt.mp.dao.dao.TbFeedbackMapper;
import com.cn.xt.mp.dao.readonlydao.ReadonlyTbCompanyUserMapper;
import com.cn.xt.mp.dao.readonlydao.ReadonlyTbFeedbackMapper;
import com.cn.xt.mp.mpModel.TbFeedback;
import com.cn.xt.mp.mpModel.TbFeedbackRecord;
import com.cn.xt.mp.service.ICompanyUserService;
import com.cn.xt.mp.service.IFeedbackRecordService;
import com.cn.xt.mp.service.impl.CompanyUserServiceImpl;
import com.cn.xt.mp.vo.CompanyUserVO;
import com.cn.xt.mp.vo.TbFeedbackVO;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    public IFeedbackRecordService feedbackRecordService;

    @Autowired
    public ReadonlyTbCompanyUserMapper readonlyTbCompanyUserMapper;
    @Autowired
    public ReadonlyTbFeedbackMapper readonlyTbFeedbackMapper;
    @Autowired
    public TbFeedbackMapper tbFeedbackMapper;


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

    @GetMapping("getFeedbackDetail/{id}")
    public Msg getFeedbackDetail(@PathVariable String id) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        TbFeedbackVO feedback = companyUserService.selectByPrimaryKey(TbFeedbackVO.class,id);
        if(null == feedback){
              return Msg.fail("反馈单不存在或已被删除");
        }
        List<TbFeedbackRecord> records = feedbackRecordService.getFeedbackRecordByFbId(id);
        feedback.setRecords(records);
        if(feedback.getType() == Constants.RECORD.ACCEPT.getCode()){//说明处理完
            List<TbFeedbackRecord> collect = records.stream().filter(e -> e.getType() == Constants.RECORD.ACCEPT.getCode()).collect(Collectors.toList());
            if(collect!=null&&collect.size()>0){
                feedback.setReplyRecord(collect.get(0));
            }
        }

        return Msg.success().add("feedback",feedback);
    }

    @PostMapping("updateFeedback")
    public Msg updateFeedback(TbFeedback feedback) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        if(StringUtils.isEmpty(feedback.getTitle())||StringUtils.isEmpty(feedback.getContent())){
            return  Msg.fail(Tips.FEEBACK_MAIN_NULL.getCode(),Tips.FEEBACK_MAIN_NULL.getDesc());
        }
        TbFeedbackVO feedback0 = companyUserService.selectByPrimaryKey(TbFeedbackVO.class,feedback.getId());
        if(null == feedback0){
            return Msg.fail("反馈单不存在或已被删除");
        }
        companyUserService.updateOne(feedback,true);
        return Msg.success();
    }

    @RequestMapping("deleteFeedback")
    public Msg deleteFeedback(@PathVariable String[] id) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        if(id.length<1){
            return  Msg.fail("删除的id不能为空");
        }
        List<TbFeedback> tbFeedbacks = companyUserService.selectByPrimaryKey(TbFeedback.class, id);
        if(tbFeedbacks != null & tbFeedbacks.size()>0){
            List<TbFeedback> collect = tbFeedbacks.stream().filter(e -> !e.getCreator().equals(userVO.getOpenid())).collect(Collectors.toList());
            if(collect != null & collect.size()>0){
                return  Msg.fail("不能删除非自己创建的反馈单");
            }
        }else {
            return  Msg.fail("删除的数据不存在");
        }
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
