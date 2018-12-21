package com.cn.xt.mp.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbFeedbackRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReadonlyTbFeedbackRecordMapper extends BasicMapper {
    TbFeedbackRecord selectByPrimaryKey(String id);

    @Select("select * from tb_feedback_record where feedback_id = #{fbId} order by create_time desc")
    List<TbFeedbackRecord> getFeedbackRecordByFbId(@Param("fbId") String fbId);
}