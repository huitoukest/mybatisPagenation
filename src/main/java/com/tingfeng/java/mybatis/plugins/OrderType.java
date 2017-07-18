package com.tingfeng.java.mybatis.plugins;

public enum OrderType {
	
	ASC("asc","升序"),DESC("desc","降序");
	private String value;
	private String remark;
	private OrderType(String value,String remark){
	this.value = value;
	this.remark = remark;
	}
	
	public String getValue(){
		return this.value;
	}
	public String getRemark(){
		return this.remark;
	}
}
