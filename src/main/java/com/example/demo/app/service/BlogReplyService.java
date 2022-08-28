package com.example.demo.app.service;

import java.util.List;

import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;

/**
 * ブログ返信サービスインターフェース
 * @author nanai
 *
 */
public interface BlogReplyService {
	
	/**
	 * 保存
	 * @param model
	 */
	void save(BlogReplyModel model);
	
	/**
	 * 削除
	 * @patam id
	 */
	void delete(BlogReplyId id);
	
	/**
	 * ブログIDによる削除
	 * @param blogid
	 */
	void delete_byBlogid(BlogId blogid);
	
	/**
	 * 全て選択
	 * @return ブログ返信モデルリスト
	 */
	List<BlogReplyModel> getAll();
	
	/**
	 * ブログIDによる選択
	 * @param id
	 * @return ブログ返信モデルリスト
	 */
	List<BlogReplyModel> select_byBlogId(BlogId id);
	
	/**
	 * IDによる選択
	 * @param id
	 * @return ブログ返信モデル
	 */
	BlogReplyModel select(BlogReplyId id);
	
	/**
	 * いいね数加算
	 * @param id
	 * @return　いいね数
	 */
	int thanksIncrement(BlogReplyId id);

}
