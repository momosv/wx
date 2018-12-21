package com.cn.xt.mp.service.impl;

import com.cn.xt.mp.base.mybatis.model.BasicExample;
import com.cn.xt.mp.base.mybatis.model.BasicExample.Criteria;
import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.dao.dao.TbFeedbackMapper;
import com.cn.xt.mp.dao.dao.TbFeedbackRecordMapper;
import com.cn.xt.mp.dao.readonlydao.ReadonlyTbFeedbackMapper;
import com.cn.xt.mp.dao.readonlydao.ReadonlyTbFeedbackRecordMapper;
import com.cn.xt.mp.mpModel.TbFeedback;
import com.cn.xt.mp.service.IFeedbackService;
import com.cn.xt.mp.vo.CompanyUserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/20 11:25
 **/
@Service("feedbackService")
public class FeedbackServiceImpl extends BasicServiceImpl implements IFeedbackService {

    @Autowired
    public ReadonlyTbFeedbackMapper readonlyTbFeedbackMapper;
    @Autowired
    public TbFeedbackMapper tbFeedbackMapper;

    @Autowired
    private void setMapper(){
        setMapper(tbFeedbackMapper);
    }

    @Autowired
    public TbFeedbackRecordMapper tbFeedbackRecordMapper;
    @Autowired
    public ReadonlyTbFeedbackRecordMapper readonlyTbFeedbackRecordMapper;

    public Object getFeedbackList(){
        return null;
    }


    @Override
    public PageInfo<TbFeedback> getFeedbackListPage(String title, Integer recordType, int pageNum, int pageSize, CompanyUserVO userVO) throws Exception {
        Page page = PageHelper.startPage(pageNum, pageSize);
        //sql
        BasicExample example = new BasicExample(TbFeedback.class);
        example.setCol(" title , create_time,record_type ");
        Criteria criteria= example.createCriteria();
        if(StringUtils.isEmpty(title)) {
            criteria.andVarFullLike("title", title);
        }
        if(null!=recordType){
            criteria.andVarEqualTo("record_type", recordType.toString());
        }
        criteria.andJoin("( creator =  '"+userVO.getOpenid()+"' or company_id = '"+userVO.getCompanyId()+"')" );
        example.setOrderByClause(" create_time desc ");

        this.selectByExample(example);
        PageInfo<TbFeedback> pageInfo =  new PageInfo(page.getResult());

        return pageInfo;
    }
}



