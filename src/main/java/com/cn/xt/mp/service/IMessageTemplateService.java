package com.cn.xt.mp.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.mpModel.MessageTemplate;

public interface IMessageTemplateService extends BasicService {

    MessageTemplate getMessageTemplateByType(String appId, Integer messageType);
}
