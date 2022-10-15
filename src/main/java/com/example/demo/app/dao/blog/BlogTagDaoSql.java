package com.example.demo.app.dao.blog;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.blog.BlogTagId;
import com.example.demo.common.word.TagWord;

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
		String sql = "INSERT INTO blog_tag(tag) "
				+ "VALUES(?)";
		
		try {
			this.jdbcTemp.update(
					sql,
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
		String sql = "UPDATE blog_tag SET "
				+ "tag = ? WHERE id = ?";
		
		return this.jdbcTemp.update(
				sql,
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
		String sql = "DELETE FROM blog_tag "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
				sql, 
				id.getId());
	}

	/**
	 * 全て選択
	 * @return ブログタグモデルリスト
	 */
	@Override
	public List<BlogTagModel> getAll() {
		String sql = "SELECT * FROM blog_tag";
		List<BlogTagModel> list = new ArrayList<BlogTagModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for( Map<String, Object> result : resultList ) {
				BlogTagModel model = this.makeModel(result);
				if(model == null)	continue;
				
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
			if( result == null ) return null;
			
			model = this.makeModel(result);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			model = null;
		}
		
		return model;
	}
	
	/**
	 * モデル生成
	 * @param  result
	 * @return ブログタグモデル
	 */
	private BlogTagModel makeModel(Map<String, Object> result) {
		if(result == null)	return null;
		
		BlogTagModel model =  new BlogTagModel(
				new BlogTagId((int)result.get(WebConsts.SQL_ID_NAME)),
				new TagWord((String)result.get(WebConsts.SQL_TAG_NAME))
				);
		
		return model;
	}
}
