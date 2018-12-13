package com.cn.xt.mp.service.impl;

import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.dao.dao.TbCompanyUserMapper;
import com.cn.xt.mp.dao.readonlydao.ReadonlyTbCompanyUserMapper;
import com.cn.xt.mp.mpModel.TbCompanyUser;
import com.cn.xt.mp.service.ICompanyUserService;
import com.cn.xt.mp.vo.CompanyUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 20:07
 **/
@Service("companyUserService")
public class CompanyUserServiceImpl extends BasicServiceImpl implements ICompanyUserService {

    @Autowired
    TbCompanyUserMapper tbCompanyUserMapper;
    @Autowired
    ReadonlyTbCompanyUserMapper readonlyTbCompanyUserMapper;
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

}
