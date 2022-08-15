package com.example.demo.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.dao.BlogReplyDao;
import com.example.demo.app.entity.BlogReplyModel;
import com.example.demo.app.exception.WebMvcConfig;

@Service
public class BlogReplyServiceUse implements BlogReplyService {
	
	private final BlogReplyDao dao;
	
	@Autowired
	public BlogReplyServiceUse(BlogReplyDao dao) {
		this.dao = dao;
	}

	@Override
	public void save(BlogReplyModel model) {
		// TODO 保存
		dao.insertReply(model);
	}

	@Override
	public void delete(int id) {
		// TODO 削除
		if(dao.deleteReply(id) == 0) {
			throw WebMvcConfig.NOT_FOUND();
		}
	}
	
	@Override
	public void delete_byBlogid(int blogid) {
		// TODO ブログIDによる削除
		dao.deleteReply_byBlog(blogid);
	}

	@Override
	public List<BlogReplyModel> getAll() {
		// TODO 全選択
		return dao.getAll();
	}
	
	@Override
	public List<BlogReplyModel> select_byBlogId(int id) {
		// TODO ブログIDによる返信リストを作成
		return dao.select_blogId(id);
	}

	@Override
	public BlogReplyModel select(int id) {
		// TODO IDによる選択
		return dao.select(id);
	}

	@Override
	public int thanksIncrement(int id) {
		// TODO いいね数加算
		return dao.thanksIncrement(id);
	}

	
}
