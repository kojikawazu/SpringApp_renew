package com.example.demo.app.home;

import java.util.ArrayList;
import java.util.List;

/**
 * ページコントローラー
 * @author nanai
 *
 */
public class PageController {
	
	/** カレントID */
	private int currentID;
	
	/** リストサイズ */
	private int listSize;
	
	/** ページサイズ */
	private int pageSize;
	
	/** ページカウンタ */
	private int page1Size;
	
	/** ページ番号リスト */
	private List<Integer> pageNumberList;
	
	/** ページ名前 */
	private String pageName;
	
	/** --------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public PageController() {
		
	}
	
	/**
	 * ページ設定
	 * @param  <T>
	 * @param  inputList
	 * @param  currentPageIdx
	 * @param  page1Size
	 * @return T型リスト
	 */
	public <T> List<T> setPaging(List<T> inputList, int currentPageIdx, int page1Size) {
		List<T> outputList  = new ArrayList<T>();
		this.pageNumberList = new ArrayList<Integer>();
		
		this.currentID      = currentPageIdx;
		this.page1Size      = page1Size;
		this.listSize       = inputList.size();
		this.pageSize       = (int)(this.listSize / this.page1Size) + 1;
		int pageIdx         = ((currentPageIdx-1) * this.page1Size);
		
		// ページによるリストの再作成
		for(int cnt=0, idx=pageIdx, pagelen=this.page1Size; 
				idx<this.listSize && cnt<pagelen; 
				idx++, cnt++){
			outputList.add(inputList.get(idx));
		}
		for(int cnt=1; cnt<this.pageSize+1; cnt++) {
			this.pageNumberList.add(cnt);
		}
		
		return outputList;
	}

	/** --------------------------------------------------------------- */
	
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

	public int getPage1Size() {
		return page1Size;
	}

	public void setPage1Size(int page1Size) {
		this.page1Size = page1Size;
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
