package com.cn.xt.mp.feedback.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.feedback.mpModel.TbFeedbackRecord;

import java.util.List;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/20 11:23
 **/
public interface IFeedbackRecordService extends BasicService {
    /**
     * 获取评论
     * @param fbId
     * @return
     */
    List<TbFeedbackRecord> getFeedbackRecordByFbId(String fbId);
}
