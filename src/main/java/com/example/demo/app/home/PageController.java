package com.example.demo.app.home;

import java.util.ArrayList;
import java.util.List;

/**
 * ページコントローラー
 * @author nanai
 *
 */
public class PageController {

	/** 現在のページ番号 */
	private int currentPageIdx;

	/** リストサイズ */
	private int listSize;

	/** 1ページに対するモデルの数 */
	private int page1ModelSize;

	/** ページの合計 */
	private int pageSize;

	/** ページ番号リスト */
	private List<Integer> pageNumberList;

	/** ページ名前 */
	private String pageName;

	/** --------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 */
	public PageController() {
		this.currentPageIdx = 0;
		this.listSize 		= 0;
		this.page1ModelSize	= 0;
		this.pageSize		= 0;
		this.pageNumberList = new ArrayList<Integer>();
		this.pageName		= "";
	}

	/** --------------------------------------------------------------- */

	/**
	 * ページ設定
	 * @param  <T>
	 * @param  inputList {@link List}<T>
	 * @param  currentPageIdx 現在のページ番号
	 * @param  page1ModelSize 1ページに対するモデル数
	 * @return T型リスト {@link List}<T>
	 */
	public <T> List<T> setPaging(List<T> inputList, int currentPageIdx, int page1ModelSize) {
		List<T> outputList  = new ArrayList<T>();
		if (inputList == null || inputList.size() == 0)				return outputList;
		if (currentPageIdx <= 0 || currentPageIdx > page1ModelSize)	return outputList;

		this.pageNumberList = new ArrayList<Integer>();
		this.currentPageIdx	= currentPageIdx;
		this.page1ModelSize	= page1ModelSize;
		this.listSize		= inputList.size();
		this.pageSize		= (int)(this.listSize / this.page1ModelSize) + (((int)this.listSize % this.page1ModelSize) == 0 ? 0 : 1);
		int pageIdx			= ((this.currentPageIdx - 1) * this.page1ModelSize);

		// ページによるリストの再作成
		for (int cnt = 0, idx = pageIdx, pagelen = this.page1ModelSize; 
				idx < this.listSize && cnt < pagelen; 
				idx++, cnt++) {
			outputList.add(inputList.get(idx));
		}
		// ページ番号リストの作成
		for (int cnt = 1; cnt < this.pageSize + 1; cnt++) {
			this.pageNumberList.add(cnt);
		}

		return outputList;
	}

	/** --------------------------------------------------------------- */

	/**
	 * getter
	 * @return currentPageIdx
	 */
	public int getCurrentPageIdx() {
		return currentPageIdx;
	}

	/**
	 * setter
	 * @param currentPageIdx
	 */
	public void setCurrentPageIdx(int currentPageIdx) {
		this.currentPageIdx = currentPageIdx;
	}

	/**
	 * getter
	 * @return listSize
	 */
	public int getListSize() {
		return listSize;
	}

	/**
	 * setter
	 * @param listSize
	 */
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	/**
	 * getter
	 * @return page1ModelSize
	 */
	public int getPage1Size() {
		return page1ModelSize;
	}

	/**
	 * setter
	 * @param page1ModelSize
	 */
	public void setPage1Size(int page1ModelSize) {
		this.page1ModelSize = page1ModelSize;
	}

	/**
	 * getter
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * setter
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * getter
	 * @return {@link List}<{@link Integer}>
	 */
	public List<Integer> getPageNumberList() {
		return pageNumberList;
	}

	/**
	 * setter
	 * @param pageNumberList {@link List}<{@link Integer}>
	 */
	public void setPageNumberList(List<Integer> pageNumberList) {
		if (pageNumberList == null)	return;
		this.pageNumberList.clear();
		for (int idx=0, len=pageNumberList.size(); idx<len; idx++) {
			this.pageNumberList.add(pageNumberList.get(idx));
		}
	}

	/**
	 * getter
	 * @return {@link String}
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * setter
	 * @param pageName {@link String}
	 */
	public void setPageName(String pageName) {
		if (pageName == null)	return;
		this.pageName = pageName;
	}
}
