package com.cn.xt.mp.feedback.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.feedback.mpModel.TbCompany;

public interface ICompanyService  extends BasicService {
    TbCompany getCompanyByCode(String code);
}
