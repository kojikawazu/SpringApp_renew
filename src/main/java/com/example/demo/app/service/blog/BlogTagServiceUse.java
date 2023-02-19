package com.example.demo.app.service.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.dao.blog.BlogTagDao;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;

/**
 * ブログタグサービスクラス
 * @author nanai
 *
 */
@Service
public class BlogTagServiceUse implements BlogTagService {
	
	/** 
	 * Daoクラス
	 * {@link BlogTagDao}
	 */
	private final BlogTagDao dao;
	
	/**
	 * コンストラクタ
	 * @param dao {@link BlogTagDao}
	 */
	@Autowired
	public BlogTagServiceUse(BlogTagDao dao) {
		this.dao = dao;
	}

	/**
	 * 保存
	 * @param model {@link BlogTagModel}
	 */
	@Override
	public void save(BlogTagModel model) {
		this.dao.insertTag(model);
	}

	/**
	 * 更新
	 * @param model {@link BlogTagModel}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	@Override
	public void update(BlogTagModel model) {
		if (this.dao.updateTag(model) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_UPDATE();
		}
	}

	/**
	 * 削除
	 * @param  id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#SQL_NOT_DELETE()}
	 */
	@Override
	public void delete(BlogTagId id) {
		if (this.dao.deleteTag(id) <= WebConsts.ERROR_DB_STATUS) {
			throw WebMvcConfig.SQL_NOT_DELETE();
		}
	}

	/**
	 * 全選択
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログタグモデルリスト {@link List}({@link BlogTagModel})
	 */
	@Override
	public List<BlogTagModel> getAll() {
		List<BlogTagModel> list = this.dao.getAll();
		
		if (list.isEmpty()) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return list;
	}
	
	/**
	 * 選択
	 * @param  id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログタグモデル {@link BlogTagModel}
	 */
	@Override
	public BlogTagModel select(BlogTagId id) {
		BlogTagModel model = this.dao.select(id);
		
		if (model == null) {
			throw WebMvcConfig.NOT_FOUND();
		}
		
		return model;
	}

	/**
	 * タグがあるかどうかチェック
	 * @param tag
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byTag(String tag) {
		return this.dao.isSelect_byTag(tag);
	}
}
