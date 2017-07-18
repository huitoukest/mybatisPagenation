package com.tingfeng.java.mybatis.plugins;

import java.io.Serializable;

public interface PaginationI  extends Serializable  {
	/**
	 * @return 数据偏移量
	 */
	public int getOffset();
	/**
	 * @return 返回记录数量限制
	 */
	public int getLimit();

}
