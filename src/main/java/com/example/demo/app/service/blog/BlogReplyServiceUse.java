package com.example.demo.app.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.blog.BlogReplyDao;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogId;
import com.example.demo.common.id.blog.BlogReplyId;

/**
 * ブログ返信サービスクラス
 * @author nanai
 *
 */
@Service
public class BlogReplyServiceUse implements BlogReplyService {
	
	/** 
	 * Daoクラス
	 * {@link BlogReplyDao}
	 */
	private final BlogReplyDao dao;
	
	/**
	 * コンストラクタ
	 * @param dao {@link BlogReplyDao}
	 */
	@Autowired
	public BlogReplyServiceUse(BlogReplyDao dao) {
		this.dao = dao;
	}

	/**
	 * 保存
	 * @param model {@link BlogReplyModel}
	 */
	@Override
	public void save(BlogReplyModel model) {
		this.dao.insertReply(model);
	}

	/**
	 * 削除
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(BlogReplyId id) {
		if (this.dao.deleteReply(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}
	
	/**
	 * ブログIDによる削除
	 * @param  blogid {@link BlogId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete_byBlogid(BlogId blogid) {
		if (this.dao.deleteReply_byBlog(blogid) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全て選択
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログ返信モデルリスト {@link List}({@link BlogReplyModel})
	 */
	@Override
	public List<BlogReplyModel> getAll() {
		List<BlogReplyModel> list = this.dao.getAll();
		
		if (list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
	}
	
	/**
	 * ブログIDによる選択
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログ返信モデルリスト {@link List}({@link BlogReplyModel})
	 */
	@Override
	public List<BlogReplyModel> select_byBlogId(BlogId id) {
		List<BlogReplyModel> list = this.dao.select_blogId(id);
		
		if (list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
	}

	/**
	 * IDによる選択
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログ返信モデル {@link BlogReplyModel}
	 */
	@Override
	public BlogReplyModel select(BlogReplyId id) {
		BlogReplyModel model = this.dao.select(id);
		
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

	/**
	 * いいね数加算
	 * @param  id {@link BlogReplyId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return いいね数
	 */
	@Override
	public int thanksIncrement(BlogReplyId id) {
		int number = this.dao.thanksIncrement(id);
		
		if (number == WebConsts.ERROR_NUMBER) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return number;
	}
}
