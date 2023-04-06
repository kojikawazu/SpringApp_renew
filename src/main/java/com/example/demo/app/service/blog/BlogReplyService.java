package com.example.demo.app.service.blog;

import java.util.List;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.common.id.blog.BlogReplyId;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.app.exception.WebMvcConfig;

/**
 * ブログ返信サービスインターフェース
 * @author nanai
 *
 */
public interface BlogReplyService {

	/**
	 * ブログIDによる削除
	 * @param blogid {@link BlogId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	void delete_byBlogid(BlogId blogid);

	/**
	 * ブログIDによる選択
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログ返信モデルリスト {@link List}({@link BlogReplyModel})
	 */
	List<BlogReplyModel> select_byBlogId(BlogId id);
	
	/**
	 * IDによる選択
	 * @param id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログ返信モデル {@link BlogReplyModel}
	 */
	BlogReplyModel select(BlogReplyId id);
	
	/**
	 * いいね数加算
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return　いいね数
	 */
	int thanksIncrement(BlogReplyId id);

}
