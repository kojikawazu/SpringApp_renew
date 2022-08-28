package com.example.demo.app.dao;

import java.util.List;

import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.common.id.BlogId;
import com.example.demo.common.id.BlogReplyId;

/**
 * ブログ返信Daoインターフェース
 * @author nanai
 *
 */
public interface BlogReplyDao {
	
	/**
	 * 追加
	 * @param モデル
	 */
	void insertReply(BlogReplyModel model);
	
	/**
	 * 削除
	 * @param id
	 * @return 0以下 失敗 それ以外 成功
	 * 
	 */
	int deleteReply(BlogReplyId id);
	
	/**
	 * ブログIDによる削除
	 * @param blogid
	 * @return
	 */
	int deleteReply_byBlog(BlogId blogid);
	
	/**
	 * 全て選択
	 * @return ブログ返信モデルリスト
	 */
	List<BlogReplyModel> getAll();
	
	/**
	 * ブログIDによる選択
	 * @param  blogid
	 * @return ブログ返信モデルリスト
	 */
	List<BlogReplyModel> select_blogId(BlogId blogid);
	
	/**
	 * ブログ返信IDによる選択
	 * @param  id
	 * @return ブログ返信モデル
	 */
	BlogReplyModel select(BlogReplyId id);
	
	/**
	 * いいね数加算
	 * @param  id
	 * @return いいね数
	 */
	int thanksIncrement(BlogReplyId id);

}
