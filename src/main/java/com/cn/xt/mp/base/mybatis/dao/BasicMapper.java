/**  
 * @Title: BasicMapper.java
 * @Package com.yjw.andy.dao
 * @Description: TODO
 * @author 余健文
 * @date 2016年9月19日
 */
package com.cn.xt.mp.base.mybatis.dao;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface BasicMapper<T ,E> {
    /**
     * 
     * @Title:统计，public all
     * @Description:
     * @param@param example
     * @param@return
     * @return
     * @创建人:LinShengwen
     * @创建时间:2017年12月5日 下午11:58:43
     * @修改人:
     * @修改原因:
     * @修改时间:
     * @版本:
     */
    int countByExample(@Param("example") E example);
    /*
     * public
     */
    int deleteByExample(@Param("example") E example);

    /*
     * public
     */
    int deleteByPrimaryKey(@Param("pks") String[] pks, @Param("pkName") String pkName, @Param("tbName") String tbName);


    List<Map> selectByExample(@Param("example") E example);

    List<Map> selectJoint(@Param("example") E example);

	List<Map> selectByPrimaryKey0(@Param("pks") String[] pks, @Param("pkName") String pkName, @Param("tbName") String tbName);


    /*
     * public
     */
    int updateByExample(@Param("dataMap") Map<String, Object> map);


    /**
	 *
	 * @Title:插入
	 * @Description:public all
	 * @param@param map
	 * @param@return
	 * @return
	 * @创建人:LinShengwen
	 * @创建时间:2017年12月6日 上午12:16:12
	 * @修改人:
	 * @修改原因:
	 * @修改时间:
	 * @版本:
	 */
	public  int insertBatch(@Param("dataMap") Map<String, Object> map);

	/**
	 *
	 * @Title:更新
	 * @Description:public all
	 * @param@param map
	 * @param@return
	 * @return
	 * @创建人:LinShengwen
	 * @创建时间:2017年12月6日 上午12:16:12
	 * @修改人:
	 * @修改原因:
	 * @修改时间:
	 * @版本:
	 */
	public  int updateBatch(@Param("dataMap") Map<String, Object> map);

	/**
	 *
	 * @Title:插入
	 * @Description:public all
	 * @param@param map
	 * @param@return
	 * @return
	 * @创建人:LinShengwen
	 * @创建时间:2017年12月6日 上午12:16:12
	 * @修改人:
	 * @修改原因:
	 * @修改时间:
	 * @版本:
	 */
	int insert(@Param("dataMap") Map<String, Object> map);
}   
