package com.example.demo.app.home;

import java.util.List;

public class PageController {
	
	private int currentID;
	
	private int listSize;
	
	private int pageSize;
	
	private int page1Cnt;
	
	private List<Integer> pageNumberList;
	
	private String pageName;

	public PageController() {
		super();
	}

	public int getCurrentID() {
		return currentID;
	}

	public void setCurrentID(int currentID) {
		this.currentID = currentID;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage1Cnt() {
		return page1Cnt;
	}

	public void setPage1Cnt(int page1Cnt) {
		this.page1Cnt = page1Cnt;
	}
	
	public List<Integer> getPageNumberList() {
		return pageNumberList;
	}

	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
}
