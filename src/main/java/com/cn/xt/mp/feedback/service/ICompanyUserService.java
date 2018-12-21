package com.cn.xt.mp.feedback.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.feedback.mpModel.TbCompanyUser;
import com.cn.xt.mp.feedback.mpModel.TbFeedback;
import com.cn.xt.mp.feedback.vo.CompanyUserVO;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 20:07
 **/
public interface ICompanyUserService extends BasicService {
    TbCompanyUser getCompanyUserByOpenid(String openid);

    CompanyUserVO getCompanyVOUserByOpenid(String openid);

    /**
     * 新增
     * @param feeback
     * @param userVO
     */
    void addFeedback(TbFeedback feeback, CompanyUserVO userVO) throws Exception;
}
