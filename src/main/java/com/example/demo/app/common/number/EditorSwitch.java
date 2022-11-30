package com.example.demo.app.common.number;

/**
 * 編集 or 投稿スイッチ
 * @author nanai
 *
 */
public class EditorSwitch {

	/** 編集番号 */
	private int editorNumber;
	
	/**
	 * 編集 or 投稿
	 * false 投稿 true 編集
	 */
	private boolean editorFlg;
	
	/**
	 * コンストラクタ
	 * @param editor
	 */
	public EditorSwitch(int editor){
		this.editorNumber = editor;
		this.editorFlg    = (editor > 0);
	}
	
	/**
	 * 編集モードか？
	 * @return false 投稿 true 編集
	 */
	public boolean isEdit() {
		return this.editorFlg;
	}
	
	/**
	 * 編集番号の取得
	 * @return 編集番号
	 */
	public int getEditorNumber() {
		return this.editorNumber;
	}
}
