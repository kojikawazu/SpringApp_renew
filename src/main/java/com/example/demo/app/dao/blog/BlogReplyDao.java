package com.example.demo.app.dao.blog;

import java.util.List;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.entity.blog.BlogReplyModel;

/**
 * ブログ返信Daoインターフェース
 * @author nanai
 *
 */
public interface BlogReplyDao {

	/**
	 * ブログIDによる削除
	 * @param blogid {@link BlogId}
	 * @return 0以下 失敗 それ以外 成功
	 */
	int delete_byBlogId(BlogId blogid);

	/**
	 * ブログIDによる選択
	 * @param  blogid
	 * @return ブログ返信モデルリスト
	 */
	List<BlogReplyModel> select_byBlogId(BlogId blogid);

	/**
	 * いいね数加算
	 * @param  id
	 * @return いいね数
	 */
	int thanksIncrement(BlogReplyId id);
}
