package com.cn.xt.mp.feedback.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.feedback.mpModel.MessageTemplate;

public interface IMessageTemplateService extends BasicService {

    MessageTemplate getMessageTemplateByType(String appId, Integer messageType);
}
