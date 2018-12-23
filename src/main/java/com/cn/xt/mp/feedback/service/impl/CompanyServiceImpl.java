package com.cn.xt.mp.feedback.service.impl;

import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.base.util.DatePattern;
import com.cn.xt.mp.base.util.XDateUtils;
import com.cn.xt.mp.feedback.dao.dao.TbCompanyMapper;
import com.cn.xt.mp.feedback.dao.dao.TbCompanyUserMapper;
import com.cn.xt.mp.feedback.dao.readonlydao.ReadonlyTbCompanyMapper;
import com.cn.xt.mp.feedback.dao.readonlydao.ReadonlyTbCompanyUserMapper;
import com.cn.xt.mp.feedback.mpModel.*;
import com.cn.xt.mp.feedback.service.ICompanyService;
import com.cn.xt.mp.feedback.service.IMessageTemplateService;
import com.cn.xt.mp.feedback.util.WxImgUtil;
import com.cn.xt.mp.feedback.vo.CompanyUserVO;
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
import java.util.HashMap;
import java.util.Map;

import static com.cn.xt.mp.base.util.Constants.UPLOAD_DIR_ROOT;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 20:07
 **/
@Transactional
@Service("companyService")
public class CompanyServiceImpl extends BasicServiceImpl implements ICompanyService {

    @Autowired
    TbCompanyMapper tbCompanyMapper;
    @Autowired
    ReadonlyTbCompanyMapper readonlyTbCompanyMapper;

    @Autowired
    IMessageTemplateService messageTemplateService;

    @Autowired
    public void setTbCompanyUserMapper(){
        this.setMapper(tbCompanyMapper);
    }

    @Override
    public TbCompany getCompanyByCode(String code){
       return readonlyTbCompanyMapper.getCompanyByCode(code);
    }


}
