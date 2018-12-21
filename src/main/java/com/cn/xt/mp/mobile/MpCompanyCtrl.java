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
import com.cn.xt.mp.service.IFeedbackService;
import com.cn.xt.mp.service.impl.CompanyUserServiceImpl;
import com.cn.xt.mp.vo.CompanyUserVO;
import com.cn.xt.mp.vo.TbFeedbackVO;
import com.cn.xt.mp.wxSecurity.service.TempMaterialService;
import com.cn.xt.mp.wxSecurity.wxentity.message.TemplateData;
import com.cn.xt.mp.wxSecurity.wxentity.message.WeChatTemplate;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
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
    public IFeedbackService feedbackService;

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
       feedback.setReplyType(Constants.RECORD.PUT.getCode());
       companyUserService.addFeedback(feedback,userVO);
        return Msg.success("保存成功");
    }


    @ApiOperation(value = "查看反馈单详情",notes = "查看反馈单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="反馈单id",dataType="string", paramType = "query" ,required = true),
    })
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

    @ApiOperation(value = "更新反馈单",notes = "更新反馈单")
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
        if(!userVO.getOpenid().equals(feedback.getCreator())){
            return Msg.fail("您不是反馈单的创建人,没权限编辑");
        }
        feedback.setUpdateTime(new Date());
        companyUserService.updateOne(feedback,true);
        return Msg.success();
    }

    @ApiOperation(value = "删除反馈单",notes = "删除反馈单",httpMethod ="POST" )
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="反馈单id,多个用英文逗号“,”分割",dataType="string", paramType = "query" ,required = true),
    })
    @RequestMapping("deleteFeedback")
    public Msg deleteFeedback(String[] id) throws Exception {
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

    //       PUT(0,"提交"),
    //       TRANSMI(1,"转发"),
    //       AGENCY_REBUT(2,"中介驳回"),
    //       ACCEPT (3,"受理"),
    //       UNIT_REBUT (4,"驳回"),
    @ApiOperation(value = "企业用户反馈单列表",notes = "反馈单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="标题",dataType="string", paramType = "query",example="被乱罚款了"),
            @ApiImplicitParam(name="recordType",value="当前状态：" +
                    " (0,\"提交\")," +
                    " (1,\"已转交（企业用户来说是未处理）\")," +
                    " (2,\"中介驳（企业用户来说是未处理）\")," +
                    " (3,\"已处理\")," +
                    " (4,\"驳回\"),",dataType="string", paramType = "query",
                    examples=@Example({
                            @ExampleProperty(mediaType="0",value = "新提交"),
                            @ExampleProperty(mediaType="1",value = "已转交（企业用户来说是未处理）"),
                            @ExampleProperty(mediaType="2",value = "中介驳回（企业用户来说是未处理）"),
                            @ExampleProperty(mediaType="3",value = "已处理"),
                            @ExampleProperty(mediaType="4",value = "受理单位驳回"),
                    })

            ),
            @ApiImplicitParam(name="pageNum",value="页码",dataType="string", paramType = "query",example="如题",defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="页面大小（条数）",dataType="string", paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name="pageNum",value="内容",dataType="string", paramType = "query",example="如题",required = true),
    })
    @PostMapping("getFeedbackList")
    public Msg getFeedbackList(@RequestParam(required = false,defaultValue = "") String title,
                               @RequestParam(name="pageNum",defaultValue = "1") int pageNum,
                               @RequestParam(name="pageSize",defaultValue = "10")int pageSize,
                               @RequestParam(name="recordType",required = false)Integer recordType
                               ) throws Exception {
        CompanyUserVO userVO = getCompanyUser();
        PageInfo  pageInfo =  feedbackService.getFeedbackListPage(title,recordType,pageNum,pageSize,userVO);
        return Msg.success().add("page",pageInfo);
    }

    @ApiOperation(value = "评价反馈单处理结果",notes = "评价反馈单处理结果",httpMethod ="POST" )
    @RequestMapping("evaluateFeedback")
    public Msg evaluateFeedback(@RequestParam(required = true) String id,@RequestParam(required = true) String content) throws Exception {
        CompanyUserVO userVO = getCompanyUser();

        TbFeedback feedback = companyUserService.selectByPrimaryKey(TbFeedback.class,id);
        if(null == feedback){
            return Msg.fail("反馈单不存在或已被删除");
        }
        if(feedback.getReplyType().equals(Constants.RECORD.ACCEPT.getCode())){
            return Msg.fail("改单尚未处理完成，不能对其处理结果进行评价");
        }
        if(!userVO.getOpenid().equals(feedback.getCreator())){
            return Msg.fail("您不是反馈单的提交者，不能对其处理结果进行评论");
        }
        TbFeedbackRecord record = new TbFeedbackRecord(feedback,Constants.RECORD.UNIT_COMMENT);
        record.setCreateTime(new Date());
        record.setContent(content);
        record.setCreator(userVO.getId());
        record.setHeadimgurl(userVO.getHeadimgurl());
        record.setCreatorBureau(userVO.getCompanyName());
        companyUserService.insertOne(record);
        return Msg.success();
    }

}
