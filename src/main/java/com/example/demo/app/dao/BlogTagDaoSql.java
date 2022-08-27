package com.example.demo.app.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.BlogTagModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.BlogTagId;
import com.example.demo.common.word.NameWord;

/**
 * ブログタグDaoクラス
 * @author nanai
 *
 */
@Repository
public class BlogTagDaoSql implements BlogTagDao {

	/** jdbcドライバー */
	private JdbcTemplate jdbcTemp;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp
	 */
	@Autowired
	public BlogTagDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	/**
	 * 追加
	 * @param model
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public void insertTag(BlogTagModel model) {
		if(model == null)	return ;
		
		try {
			this.jdbcTemp.update("INSERT INTO blog_tag(tag) "
					+ "VALUES(?)",
					model.getTag());
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 更新
	 * @param model
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int updateTag(BlogTagModel model) {
		if(model == null)	return WebConsts.ERROR_NUMBER;
		
		return this.jdbcTemp.update("UPDATE blog_tag SET "
				+ "tag = ? WHERE id = ?",
				model.getTag(),
				model.getId());
	}

	/**
	 * 削除
	 * @param id
	 * @return 0以下 失敗 それ以外 成功 
	 */
	@Override
	public int deleteTag(BlogTagId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		
		return this.jdbcTemp.update("DELETE FROM blog_tag "
				+ "WHERE id = ?", 
				id.getId());
	}

	/**
	 * 全て選択
	 * @return ブログタグモデルリスト
	 */
	@Override
	public List<BlogTagModel> getAll() {
		String sql = "SELECT * "
				+ "FROM blog_tag";
		List<BlogTagModel> list = new ArrayList<BlogTagModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for( Map<String, Object> result : resultList ) {
				BlogTagModel model = new BlogTagModel(
						new BlogTagId((int)result.get("id")),
						new NameWord((String)result.get("tag"))
						);
				list.add(model);
			}
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}

	/**
	 * タグが存在するかチェック
	 * @param target
	 * @return true タグ存在する false タグ存在しない
	 */
	@Override
	public boolean isSelect_byTag(String target) {
		String sql = "SELECT id "
				+ "FROM blog_tag "
				+ "WHERE tag = ?";
		
		return jdbcTemp.query(sql, 
				new Object[]{ target },
				new int[] { Types.VARCHAR },
				rs -> {
			return rs.next() ? true : false;	
		});
	}

	/**
	 * 選択
	 * @param id
	 * @return ブログタグモデル
	 */
	@Override
	public BlogTagModel select(BlogTagId id) {
		String sql = "SELECT * "
				+ "FROM blog_tag "
				+ "WHERE id = ?";
		BlogTagModel model = null;
		
		try {
			Map<String, Object> result = jdbcTemp.queryForMap(sql, id.getId());
			if( result != null ) {
				model = new BlogTagModel(
						new BlogTagId((int)result.get("id")),
						new NameWord((String)result.get("tag"))
						);
			}
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			model = null;
		}
		
		return model;
	}
}
