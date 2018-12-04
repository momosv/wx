/**  
 * @Title: BasicService.java
 * @Package com.yjw.andy.service
 * @Description: TODO
 * @author 余健文
 * @date 2016年9月19日
 */
package com.cn.xt.mp.base.mybatis.service;

import cn.momosv.blog.base.mybatis.model.base.BasicExample;
import cn.momosv.blog.base.mybatis.model.base.IBaseDBPO;

import java.util.List;
import java.util.Map;


/**
 *
 * @param
 * @param
 */
public interface BasicService {

    <T extends IBaseDBPO,E extends BasicExample>int countByExample(E example);

    <T extends IBaseDBPO,E extends BasicExample>int deleteByExample(E example);

    <T extends IBaseDBPO,E extends BasicExample>int deleteByPrimaryKey(Class<T> clazz, String id) throws InstantiationException, IllegalAccessException;

    @Deprecated
    <T extends IBaseDBPO,E extends BasicExample> List<T> selectByExample(Class<T> clazz, E example)throws Exception;

    <T extends IBaseDBPO,E extends BasicExample>T selectOneByExample(E example) throws Exception;

    <T extends IBaseDBPO,E extends BasicExample>List<T> selectByExample(E example) throws Exception;

    <T extends IBaseDBPO,E extends BasicExample> List<T> selectJoint(E example) throws Exception;

    <T extends IBaseDBPO,E extends BasicExample>T selectByPrimaryKey(Class<T> clazz, String ids)throws Exception;

    <F extends IBaseDBPO> F selectByPrimaryKey(Class<F> clazz, Integer ids)throws Exception;

    <T extends IBaseDBPO,E extends BasicExample>List<T> selectByPrimaryKey(Class<T> clazz, String[] ids) throws Exception;

    <T extends IBaseDBPO,E extends BasicExample>List<T> selectByPrimaryKey(Class<T> clazz, List<String> ids) throws Exception;

    <T extends IBaseDBPO,E extends BasicExample>int updateByExample(T record, E example);

	Object getFieldValueByName(String fieldName, Object o);

	String[] getFieldName(Object o);

	Object[] getFieldValues(Object o);

	<T extends IBaseDBPO,E extends BasicExample>  int insertBatch(List<T> list);

    <T extends IBaseDBPO,E extends BasicExample>  int updateBatch(List<T> list);

    <T extends IBaseDBPO,E extends BasicExample> Map<String, Object> getFieldMapValues(List<T> list, boolean selective);

    <T extends IBaseDBPO,E extends BasicExample>int updateBatch(List<T> list, boolean selective);

    <T extends IBaseDBPO,E extends BasicExample>List<T> findAll(Class<T> t) throws Exception ;

    <T extends IBaseDBPO,E extends BasicExample>int insertOne(T record);

    <T extends IBaseDBPO,E extends BasicExample>int updateOne(T record);

   <T extends IBaseDBPO,E extends BasicExample>  int updateOne(T t, boolean selective);

    <T extends IBaseDBPO,E extends BasicExample>int updateByExample(T record, E example, boolean selective);

    <T extends IBaseDBPO,E extends BasicExample>int deleteByPrimaryKey(Class<T> clazz, List<String> id) throws InstantiationException, IllegalAccessException;

    <T extends IBaseDBPO,E extends BasicExample>int deleteByPrimaryKey(Class<T> clazz, String[] id) throws IllegalAccessException, InstantiationException;


}
