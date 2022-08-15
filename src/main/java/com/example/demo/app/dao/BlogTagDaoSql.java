package com.example.demo.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.BlogTagModel;

@Repository
public class BlogTagDaoSql implements BlogTagDao {

	private JdbcTemplate jdbcTemp;
	
	@Autowired
	public BlogTagDaoSql(JdbcTemplate jdbcTemp) {
		// TODO コンストラクタ
		this.jdbcTemp = jdbcTemp;
	}
	
	@Override
	public void insertTag(BlogTagModel model) {
		// TODO 追加処理
		jdbcTemp.update("INSERT INTO blog_tag(tag) VALUES(?)",
				model.getTag());
	}

	@Override
	public int updateTag(BlogTagModel model) {
		// TODO 更新
		return jdbcTemp.update("UPDATE blog_tag SET tag = ? WHERE id = ?",
				model.getTag(),
				model.getId());
	}

	@Override
	public int deleteTag(int id) {
		// TODO 削除
		return jdbcTemp.update("DELETE FROM blog_tag WHERE id = ?", id);
	}

	@Override
	public List<BlogTagModel> getAll() {
		// TODO 全て選択
		String sql = "SELECT id, tag FROM blog_tag";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql);
		List<BlogTagModel> list = new ArrayList<BlogTagModel>();
		
		for( Map<String, Object> result : resultList ) {
			BlogTagModel model = new BlogTagModel();
			model.setId((int)result.get("id"));
			model.setTag((String)result.get("tag"));
			list.add(model);
		}
		return list;
	}

	@Override
	public boolean isSelect_byTag(String target) {
		// TODO タグが存在するかチェック
		String sql = "SELECT id FROM blog_tag WHERE tag = ?";
		return jdbcTemp.query(sql, new Object[]{ target }, rs -> {
			return rs.next() ? true : false;	
		});
	}

	@Override
	public BlogTagModel select(int id) {
		// TODO IDによる選択
		String sql = "SELECT id, tag FROM blog_tag WHERE id = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, id);
		if( result == null )	return null;
		BlogTagModel model = new BlogTagModel();
		model.setId((int)result.get("id"));
		model.setTag((String)result.get("tag"));
		return model;
	}

}
