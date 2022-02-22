package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest {
	private Integer page;
	private Integer pageItem;
	private Sorter sorter;

	public PageRequest(Integer page, Integer pageItem, Sorter sorter) {
		this.page = page;
		this.pageItem = pageItem;
		this.sorter = sorter;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageItem() {
		return pageItem;
	}

	public void setPageItem(Integer pageItem) {
		this.pageItem = pageItem;
	}
	
	public Sorter getSorter() {
		return sorter;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}

	public Integer getOffset() {
		if (this.page != null && this.pageItem != null)
			return (this.page - 1) * this.pageItem;
		else
			return null;
	}
	
	
}
