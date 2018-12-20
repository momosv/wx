package com.cn.xt.mp.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.mpModel.TbFeedbackRecord;

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
