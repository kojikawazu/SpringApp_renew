package com.example.demo.app.entity.blog;

import java.util.List;

import com.example.demo.common.list.ListObject;

/**
 * ブログ返信モデルリスト
 * <br>
 * extends {@link ListObject}<{@link BlogReplyModel}>
 * @author nanai
 *
 */
public class BlogReplyModelList extends ListObject<BlogReplyModel> {

	/**
	 * コンストラクタ
	 */
	public BlogReplyModelList() {
		super();
	}

	/**
	 * コンストラクタ
	 * @param list {@link List}<{@link BlogReplyModel}>
	 */
	public BlogReplyModelList(List<BlogReplyModel> list) {
		super(list);
	}

	/**
	 * コンストラクタ
	 * @param list {@link BlogReplyModelList}
	 */
	public BlogReplyModelList(BlogReplyModelList list) {
		super();
		this.setList(list);
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * setter
	 * @param list {@link BlogReplyModelList}
	 */
	public void setList(BlogReplyModelList list) {
		if (list == null)	return;
		this.setList(list.getList());
	}
}
