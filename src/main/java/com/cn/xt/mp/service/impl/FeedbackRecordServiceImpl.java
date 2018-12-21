package com.cn.xt.mp.service.impl;

import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.dao.dao.TbFeedbackMapper;
import com.cn.xt.mp.dao.dao.TbFeedbackRecordMapper;
import com.cn.xt.mp.dao.readonlydao.ReadonlyTbFeedbackMapper;
import com.cn.xt.mp.dao.readonlydao.ReadonlyTbFeedbackRecordMapper;
import com.cn.xt.mp.mpModel.TbFeedbackRecord;
import com.cn.xt.mp.service.IFeedbackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/20 11:25
 **/
@Service("feedbackRecordService")
public class FeedbackRecordServiceImpl extends BasicServiceImpl implements IFeedbackRecordService {

    @Autowired
    public ReadonlyTbFeedbackMapper readonlyTbFeedbackMapper;
    @Autowired
    public TbFeedbackMapper tbFeedbackMapper;

    @Autowired
    public TbFeedbackRecordMapper tbFeedbackRecordMapper;
    @Autowired
    public ReadonlyTbFeedbackRecordMapper readonlyTbFeedbackRecordMapper;

    @Autowired
    private void setMapper(){
        setMapper(tbFeedbackRecordMapper);
    }

    @Override
    public List<TbFeedbackRecord> getFeedbackRecordByFbId(String fbId){

       return   readonlyTbFeedbackRecordMapper.getFeedbackRecordByFbId(fbId);

   }


}



