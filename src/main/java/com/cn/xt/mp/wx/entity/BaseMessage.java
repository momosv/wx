package com.cn.xt.mp.wx.entity;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/28 12:52
 **/
public class BaseMessage {



    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;


    public String getToUserName() {

        return ToUserName;

    }

    public void setToUserName(String toUserName) {

        ToUserName = toUserName;

    }

    public String getFromUserName() {

        return FromUserName;

    }

    public void setFromUserName(String fromUserName) {

        FromUserName = fromUserName;

    }

    public Long getCreateTime() {

        return CreateTime;

    }

    public void setCreateTime(long l) {

        CreateTime = l;

    }

    public String getMsgType() {

        return MsgType;

    }

    public void setMsgType(String msgType) {

        MsgType = msgType;

    }





    public BaseMessage(String toUserName, String fromUserName,

                       Long createTime, String msgType, String content, String msgId) {

        super();

        ToUserName = toUserName;

        FromUserName = fromUserName;

        CreateTime = createTime;

        MsgType = msgType;


    }

    public BaseMessage() {

        super();

    }

}
