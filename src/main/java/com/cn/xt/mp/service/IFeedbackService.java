package com.cn.xt.mp.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.mpModel.TbFeedback;
import com.cn.xt.mp.vo.CompanyUserVO;
import com.github.pagehelper.PageInfo;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/20 11:23
 **/
public interface IFeedbackService extends BasicService {

    PageInfo<TbFeedback> getFeedbackListPage(String title, Integer recordType, int pageNum, int pageSize, CompanyUserVO userVO) throws Exception;
}
