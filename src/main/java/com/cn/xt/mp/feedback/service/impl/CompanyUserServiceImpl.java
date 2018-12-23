package com.cn.xt.mp.feedback.service.impl;

import com.cn.xt.mp.base.exception.ExceptionCenter;
import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.base.util.DatePattern;
import com.cn.xt.mp.base.util.XDateUtils;
import com.cn.xt.mp.feedback.dao.dao.TbCompanyUserMapper;
import com.cn.xt.mp.feedback.dao.readonlydao.ReadonlyTbCompanyUserMapper;
import com.cn.xt.mp.feedback.mpModel.*;
import com.cn.xt.mp.feedback.service.ICompanyUserService;
import com.cn.xt.mp.feedback.service.IMessageTemplateService;
import com.cn.xt.mp.feedback.util.WxImgUtil;
import com.cn.xt.mp.feedback.vo.CompanyUserVO;
import com.cn.xt.mp.feedback.wxSecurity.service.TempMaterialService;
import com.cn.xt.mp.feedback.wxSecurity.util.WXUtil;
import com.cn.xt.mp.feedback.wxSecurity.wxentity.AccessToken;
import com.cn.xt.mp.feedback.wxSecurity.wxentity.message.TemplateData;
import com.cn.xt.mp.feedback.wxSecurity.wxentity.message.WeChatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cn.xt.mp.base.util.Constants.UPLOAD_DIR_ROOT;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 20:07
 **/
@Transactional
@Service("companyUserService")
public class CompanyUserServiceImpl extends BasicServiceImpl implements ICompanyUserService {

    @Autowired
    TbCompanyUserMapper tbCompanyUserMapper;
    @Autowired
    ReadonlyTbCompanyUserMapper readonlyTbCompanyUserMapper;

    @Autowired
    IMessageTemplateService messageTemplateService;

    @Autowired
    public void setTbCompanyUserMapper(){
        this.setMapper(tbCompanyUserMapper);
    }

    @Override
    public TbCompanyUser getCompanyUserByOpenid(String openid){
       return tbCompanyUserMapper.getCompanyUserByOpenid(openid);
    }

    @Override
    public CompanyUserVO getCompanyVOUserByOpenid(String openid){
       return readonlyTbCompanyUserMapper.getCompanyVOUserByOpenid(openid);
    }

    @Override
    public void addFeedback(TbFeedback feeback,  CompanyUserVO userVO) throws Exception {
        if(!StringUtils.isEmpty(feeback.getImg())){//此时提上来的数据还是微信的serverId
            String[] serverId = feeback.getImg().split(",");
            WxSecurityPO securityPO = WXUtil.getWxSecurityByAppId(userVO.getAppId());
            AccessToken token = WXUtil.getAccessToken(userVO.getAppId());
           String path = UPLOAD_DIR_ROOT +"/"+securityPO.getDiyDomain()+"/";
            File file = new File(path);
            if  (!file.exists()){
                file .mkdirs();
            }
            String img = WxImgUtil.saveImg(serverId,securityPO.getToken(),securityPO.getDiyDomain());
            feeback.setImg(img);
        }
        TbFeedbackRecord record = new TbFeedbackRecord(feeback,Constants.RECORD.PUT);
        record.setCreator(userVO.getId());
        record.setHeadimgurl(userVO.getHeadimgurl());
        record.setCreatorBureau(userVO.getCompanyName());
        this.insertOne(feeback);
        this.insertOne(record);
        //wx add 异步通知
        syncSendPutFeedbackMessage(feeback,userVO.getAppId());


    }
    @Async
    public void syncSendPutFeedbackMessage(TbFeedback feeback,String appId) throws Exception {
        MessageTemplate messageTemplate = messageTemplateService.getMessageTemplateByType(appId,1);
        WeChatTemplate weChatTemplate = new WeChatTemplate();
        weChatTemplate.setTemplate_id(messageTemplate.getTemplateId());
        weChatTemplate.setTouser(feeback.getCreator());//此处是用户的OpenId
        weChatTemplate.setUrl("51xt.com.cn");
        Map<String, TemplateData> m = new HashMap<String, TemplateData>();
        TemplateData first = new TemplateData();
        //first.setColor("#66CCFF");
        first.setValue("您好！您提交的投诉建议已提交成功，请关注。\n标题："+feeback.getTitle());
        m.put("first", first);//title
        TemplateData keyword1 = new TemplateData();
        //keyword1.setColor("#66CCFF");
        keyword1.setValue(XDateUtils.dateToString(feeback.getCreateTime(), DatePattern.DATE_TIME.getPattern()));//time
        m.put("keyword1", keyword1);
        TemplateData keyword2 = new TemplateData();
        // keyword2.setColor("#66CCFF");
        String content =feeback.getContent().length()>100 ? feeback.getContent().substring(0,96)+"...": feeback.getContent();
        keyword2.setValue(content);//content
        m.put("keyword2", keyword2);
        TemplateData remark = new TemplateData();
        //  remark.setColor("#66CCFF");
        remark.setValue("请关注处理动态，具体的以实际处理结果为准。");
        m.put("remark", remark);
        weChatTemplate.setData(m);
        WXUtil.sendTemplateMessage2(appId,weChatTemplate);
    }


}
