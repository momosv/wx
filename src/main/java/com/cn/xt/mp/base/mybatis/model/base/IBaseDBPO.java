package com.cn.xt.mp.base.mybatis.model.base;

import java.io.Serializable;

/**
 * @description base
 * @author Lin Shengwen
 * @dateTime 2018/1/5 9:56
 * @version
 */
public   class IBaseDBPO implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;

	 public  String _getTableName(){
	 	return "";
	 }

	 public String _getPKColumnName(){
	 	return "id";
	 };

	 public Object _getPKValue(){
	 	return null;
	 };

	 public void _setPKValue(Object var){

	 };

}
