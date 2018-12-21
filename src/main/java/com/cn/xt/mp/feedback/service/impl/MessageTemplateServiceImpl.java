package com.cn.xt.mp.feedback.service.impl;

import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.feedback.dao.dao.MessageTemplateMapper;
import com.cn.xt.mp.feedback.mpModel.MessageTemplate;
import com.cn.xt.mp.feedback.service.IMessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/19 14:38
 **/
@Service("messageTemplateService")
public class MessageTemplateServiceImpl  extends BasicServiceImpl implements IMessageTemplateService {


    @Autowired
     private MessageTemplateMapper messageTemplateMapper;
    @Autowired
    private void setMapper(){
        setMapper(messageTemplateMapper);
    }

    @Override
   @Cacheable(cacheNames="messageTemplate",key="#appId+#messageType")
    public MessageTemplate getMessageTemplateByType(String appId, Integer messageType) {
        return  messageTemplateMapper.getMessageTemplateByType(appId,messageType);
    }
}
