package com.cn.xt.mp.vo;

import com.cn.xt.mp.base.mybatis.model.IBaseDBPO;
import com.cn.xt.mp.mpModel.TbFeedbackRecord;

import java.util.Date;
import java.util.List;

public class TbFeedbackVO extends IBaseDBPO {
    private String id;

    private String code;

    private String title;

    private String content;

    private String img;

    private String reply;

    private Date incidentTime;

    private Integer type;

    private String creator;

    private Date createTime;

    private Integer createType;

    private String companyId;

    private String acceptingUnitId;

    private TbFeedbackRecord replyRecord;

    List<TbFeedbackRecord> records;

    public TbFeedbackRecord getReplyRecord() {
        return replyRecord;
    }

    public void setReplyRecord(TbFeedbackRecord replyRecord) {
        this.replyRecord = replyRecord;
    }

    public List<TbFeedbackRecord> getRecords() {
        return records;
    }

    public void setRecords(List<TbFeedbackRecord> records) {
        this.records = records;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Date getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(Date incidentTime) {
        this.incidentTime = incidentTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateType() {
        return createType;
    }

    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getAcceptingUnitId() {
        return acceptingUnitId;
    }

    public void setAcceptingUnitId(String acceptingUnitId) {
        this.acceptingUnitId = acceptingUnitId == null ? null : acceptingUnitId.trim();
    }

    public  String _getTableName(){
        return "tb_feedback";
    }

    public String _getPKColumnName(){
        return "id";
    };

    public Object _getPKValue(){
        return id;
    };

    public void _setPKValue(Object var){
        id =  var.toString();
    }
}