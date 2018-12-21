package com.cn.xt.mp.mpModel;

import com.cn.xt.mp.base.mybatis.model.IBaseDBPO;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.base.util.RandomUtils;

import java.util.Date;

public class TbFeedbackRecord  extends IBaseDBPO {
    private String id;

    private String feedbackId;

    private Date createTime;

    private String content;

   // '0 提交，1转发，2 中介驳回，3接收受理，4受理单位驳回 ，5 重新提交',
    private Integer type;

    private String creator;

    private String headimgurl;

    private String creatorBureau;

    public TbFeedbackRecord() {

    }

    public TbFeedbackRecord(TbFeedback feeback, Constants.RECORD record) {
        this.id=RandomUtils.getUUID32();
        this.feedbackId=feeback.getId();
        this.createTime=feeback.getCreateTime();
        this.content=record.getDesc();
        this.type=record.getCode();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getCreatorBureau() {
        return creatorBureau;
    }

    public void setCreatorBureau(String creatorBureau) {
        this.creatorBureau = creatorBureau;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId == null ? null : feedbackId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public  String _getTableName(){
        return "tb_feedback_record";
    }

    public String _getPKColumnName(){
        return "id";
    }

    public Object _getPKValue(){
        return id;
    }

    public void _setPKValue(Object var){
        id =  var.toString();
    }
}