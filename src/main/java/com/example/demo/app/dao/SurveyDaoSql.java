package com.example.demo.app.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.SurveyModel;

@Repository
public class SurveyDaoSql implements SurveyDao {

	private final JdbcTemplate jdbcTemp;
	
	@Autowired
	public SurveyDaoSql(JdbcTemplate jdbcTemp) {
		// TODO コンストラクタ
		this.jdbcTemp = jdbcTemp;
	}
	
	@Override
	public void insertSurvey(SurveyModel model) {
		// TODO 追加処理
		jdbcTemp.update("INSERT INTO survey(name, age, profession, ismen, satisfaction, comment, created) VALUES(?,?,?,?,?,?,?)",
				model.getName(),
				model.getAge(),
				model.getProfession(),
				model.getMen_or_female(),
				model.getSatisfaction(),
				model.getComment(),
				model.getCreated()
			);
	}

	@Override
	public int updateSurvey(SurveyModel model) {
		// TODO 更新処理
		return jdbcTemp.update("UPDATE survey SET name = ?, age = ?, profession = ?, ismen = ?, satisfaction = ?, comment = ? WHERE id = ?",
				model.getName(),
				model.getAge(),
				model.getProfession(),
				model.getMen_or_female(),
				model.getSatisfaction(),
				model.getComment(),
				model.getId()
			);
	}

	@Override
	public List<SurveyModel> getAll() {
		// TODO 全て選択処理
		String sql = "SELECT id, name, age, profession, ismen, satisfaction, comment, created FROM survey";
		List<Map<String, Object>> resultList = jdbcTemp.queryForList(sql);
		List<SurveyModel> list = new ArrayList<SurveyModel>();
		
		for(Map<String, Object> result : resultList) {
			SurveyModel model = new SurveyModel();
			model.setId((int)result.get("id"));
			model.setName((String)result.get("name"));
			model.setAge((int)result.get("age"));
			model.setProfession((int)result.get("profession"));
			model.setMen_or_female((int)result.get("ismen"));
			model.setSatisfaction((int)result.get("satisfaction"));
			model.setComment((String)result.get("comment"));
			model.setCreated(((Timestamp)result.get("created")).toLocalDateTime());
			list.add(model);
		}
		return list;
	}

	@Override
	public int countSatisfactionAll(int satis) {
		// TODO 全ての評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;
	}

	@Override
	public int countSatisfactionAge(int satis, int age) {
		// TODO 年齢による評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		if( age <= 0 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE age >= ? AND age < ? AND satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, age, age + 10, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;	
	}

	@Override
	public int countSatisfactionSx(int satis, int ismen) {
		// TODO 性別による評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		if( ismen != 1 && ismen != 2 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE ismen = ? AND satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, ismen, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;
	}

	@Override
	public int countSatisfactionProf(int satis, int prof) {
		// TODO 職業による評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		if( prof <= 0 || prof > 4 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE profession = ? AND satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, prof, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;
	}

	@Override
	public int countSatisfactionSxProf(int satis, int ismen, int prof) {
		// TODO 性別＆職業の評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		if( ismen != 1 && ismen != 2 ) return 0;
		if( prof <= 0 || prof > 4 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE ismen = ? AND profession = ? AND satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, ismen, prof, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;	
	}

	@Override
	public int countSatisfactionProfAge(int satis, int prof, int age) {
		// TODO 職業&年齢別の評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		if( age <= 0 ) return 0;
		if( prof <= 0 || prof > 4 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE age >= ? AND age < ? AND profession = ? AND satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, age, age + 10, prof, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;	
	}

	@Override
	public int countSatisfactionSxAge(int satis, int ismen, int age) {
		// TODO 性別＆年齢別の評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		if( ismen != 1 && ismen != 2 ) return 0;
		if( age <= 0 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE age >= ? AND age < ? AND ismen = ? AND satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, age, age + 10, ismen, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;	
	}

	@Override
	public int countSatisfactionSxProfAge(int satis, int ismen, int prof, int age) {
		// TODO 性別＆年齢＆職業別の評価の数を取得
		if( satis <= 0 || satis > 5 ) return 0;
		if( ismen != 1 && ismen != 2 ) return 0;
		if( prof <= 0 || prof > 4 ) return 0;
		if( age <= 0 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count FROM survey WHERE age >= ? AND age < ? AND ismen = ? AND profession = ? AND satisfaction = ?";
		Map<String, Object> result = jdbcTemp.queryForMap(sql, age, age + 10, ismen, prof, satis);
		
		long ll_count = (long)result.get("satis_count");
		int count = (int)ll_count;
		return count;	
	}
}
