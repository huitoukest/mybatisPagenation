package com.tingfeng.java.mybatis.plugins;

public class Pagination  implements PaginationI  {

    private static final long serialVersionUID = 1L;
    /**
     * 当前页面记录数
     */
    private int pageSize = 20;
    /**
     * 当前页码
     */
    private int currentPage = 1;
    
    
    /**
     * 
     * @param pageSize 当前页面记录数
     * @param currentPage 当前页码
     */
	public Pagination(int pageSize, int currentPage) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		if(this.pageSize <= 0)
		{
			this.pageSize = 0;
		}
		return this.pageSize;
	}
	
	public int getCurrentPage() {
		if(this.currentPage <= 0)
		{
			this.currentPage = 1;
		}
		return this.currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public int getOffset() {
		return (this.getCurrentPage() - 1) * this.getPageSize();
	}

	@Override
	public int getLimit() {
		return this.getPageSize();
	}
	
	
}
