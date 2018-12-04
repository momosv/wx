package com.cn.xt.mp.base.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回的类
 * 
 * @author momo
 * 
 */
public class Msg {
	//状态码   100-成功    200-失败
	private int code;
	//提示信息
	private String msg;
	
	//用户要返回给浏览器的数据
	private Map<String, Object> extend = new HashMap<String, Object>();

	public static Msg success(){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		return result;
	}
	
	public static Msg fail(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		return result;
	}
	
	public static Msg success(int code, String msg){
		Msg result = new Msg();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static Msg fail(int code, String msg){
		Msg result = new Msg();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static Msg success(String msg){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg(msg);
		return result;
	}
	
	public static Msg fail(String msg){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg(msg);
		return result;
	}
	
	public Msg add(String key, Object value){
		this.getExtend().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public Msg setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public Msg setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public Msg setExtend(Map<String, Object> extend) {
		this.extend = extend;
		return this;
	}
	
	
}
