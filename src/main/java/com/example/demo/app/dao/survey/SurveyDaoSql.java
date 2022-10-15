package com.example.demo.app.dao.survey;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.app.entity.survey.SurveyModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.survey.SurveyId;
import com.example.demo.common.number.NormalNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.NameWord;

/**
 * 調査Daoクラス
 * @author nanai
 *
 */
@Repository
public class SurveyDaoSql implements SurveyDao {

	/** jdbcドライバー */
	private final JdbcTemplate jdbcTemp;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp
	 */
	@Autowired
	public SurveyDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	/**
	 * 追加
	 * @param model
	 */
	@Override
	public void insertSurvey(SurveyModel model) {
		if(model == null)	return ;
		String sql = "INSERT INTO survey("
				+ "name, age, profession, ismen, satisfaction, comment, created) "
				+ "VALUES(?,?,?,?,?,?,?)";
		
		try {
			this.jdbcTemp.update(sql,
					model.getName(),
					model.getAge(),
					model.getProfession(),
					model.getMen_or_female(),
					model.getSatisfaction(),
					model.getComment(),
					model.getCreated()
				);
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
	public int updateSurvey(SurveyModel model) {
		if(model == null)	return WebConsts.ERROR_NUMBER;
		String sql = "UPDATE survey SET "
				+ "name = ?, age = ?, profession = ?, ismen = ?, satisfaction = ?, comment = ? "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(sql,
				model.getName(),
				model.getAge(),
				model.getProfession(),
				model.getMen_or_female(),
				model.getSatisfaction(),
				model.getComment(),
				model.getId()
			);
	}

	/**
	 * 全選択
	 * @return 調査モデルリスト
	 */
	@Override
	public List<SurveyModel> getAll() {
		String sql = "SELECT * FROM survey";
		List<SurveyModel> list = new ArrayList<SurveyModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			for(Map<String, Object> result : resultList) {
				SurveyModel model = this.makeModel(result);
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
	 * 全ての評価の数を取得
	 * @param  satis 評価
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionAll(int satis) {
		if( satis <= 0 || satis > 5 ) return 0;
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE satisfaction = ?";
		
		int count = 0;
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, satis);
			if(result == null)	return 0;
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;
	}

	/**
	 * 年齢による評価の数を取得
	 * @param  satis 評価
	 * @param  age   年齢
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionAge(int satis, int age) {
		if( satis <= 0 || satis > 5 ) 	return 0;
		if( age <= 0 ) 					return 0;
		
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE age >= ? AND age < ? "
				+ "AND satisfaction = ?";
		int count = 0;
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, age, age + 10, satis);
			if(result == null)	return 0;
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;	
	}

	/**
	 * 性別による評価の数を取得
	 * @param  satis 評価
	 * @param  ismen 男 or 女
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionSx(int satis, int ismen) {
		if( satis <= 0 || satis > 5 ) 	return 0;
		if( ismen != 1 && ismen != 2 ) 	return 0;
		
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE ismen = ? AND "
				+ "satisfaction = ?";
		int count = 0;
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, ismen, satis);
			if(result == null)	return 0;
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;
	}

	/**
	 * 職業による評価の数を取得
	 * @param  satis 評価
	 * @param  prof  職業
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionProf(int satis, int prof) {
		if( satis <= 0 || satis > 5 ) 	return 0;
		if( prof <= 0 || prof > 4 ) 	return 0;
		
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE profession = ? AND "
				+ "satisfaction = ?";
		int count = 0;
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(sql, prof, satis);
			if(result == null)	return 0;
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;
	}

	/**
	 * 性別 & 職業の評価の数を取得
	 * @param  satis 評価
	 * @param  ismen 男 or 女
	 * @param  prof  職業
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionSxProf(int satis, int ismen, int prof) {
		if( satis <= 0 || satis > 5 ) 	return 0;
		if( ismen != 1 && ismen != 2 ) 	return 0;
		if( prof <= 0 || prof > 4 ) 	return 0;
		
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE ismen = ? AND "
				+ "profession = ? AND "
				+ "satisfaction = ?";
		int count = 0;
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, ismen, prof, satis);
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;	
	}

	/**
	 * 職業 & 年齢別の評価の数
	 * @param  satis 評価
	 * @param  prof  職業
	 * @param  age   年齢
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionProfAge(int satis, int prof, int age) {
		if( satis <= 0 || satis > 5 ) 	return 0;
		if( age <= 0 ) 					return 0;
		if( prof <= 0 || prof > 4 ) 	return 0;
		
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE age >= ? AND age < ? AND "
				+ "profession = ? AND "
				+ "satisfaction = ?";
		int count = 0;
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, age, age + 10, prof, satis);
			if(result == null)	return 0;
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;	
	}

	/**
	 * 性別 & 年齢別の評価の数を取得
	 * @param  satis 評価
	 * @param  ismen 男 or 女
	 * @param  age   年齢
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionSxAge(int satis, int ismen, int age) {
		if( satis <= 0 || satis > 5 ) 	return 0;
		if( ismen != 1 && ismen != 2 ) 	return 0;
		if( age <= 0 ) 					return 0;
		
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE age >= ? AND age < ? AND "
				+ "ismen = ? AND "
				+ "satisfaction = ?";
		int count = 0;
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, age, age + 10, ismen, satis);
			if(result == null)	return 0;
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;	
	}

	/**
	 * 性別 & 年齢 & 職業別の評価の数
	 * @param satis 評価
	 * @param ismen 男 or 女
	 * @param prof  職業
	 * @param age   年齢
	 * @return 評価の数
	 */
	@Override
	public int countSatisfactionSxProfAge(int satis, int ismen, int prof, int age) {
		if( satis <= 0 || satis > 5 ) 	return 0;
		if( ismen != 1 && ismen != 2 ) 	return 0;
		if( prof <= 0 || prof > 4 ) 	return 0;
		if( age <= 0 ) 					return 0;
		
		String sql = "SELECT COUNT(*) AS satis_count "
				+ "FROM survey "
				+ "WHERE age >= ? AND age < ? AND "
				+ "ismen = ? AND "
				+ "profession = ? AND "
				+ "satisfaction = ?";
		int count = 0;
		
		try {
			Map<String, Object> result = jdbcTemp.queryForMap(sql, age, age + 10, ismen, prof, satis);
			
			long ll_count = (long)result.get(WebConsts.SQL_SATIS_COUNT);
			count = (int)ll_count;
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
		
		return count;	
	}
	
	private SurveyModel makeModel(Map<String, Object> result) {
		if(result == null)	return null;
		
		SurveyModel model = new SurveyModel(
				new SurveyId((int)result.get(WebConsts.SQL_ID_NAME)),
				new NameWord((String)result.get(WebConsts.SQL_NAME_NAME)),
				new NormalNumber((int)result.get(WebConsts.SQL_AGE_NAME)),
				new NormalNumber((int)result.get(WebConsts.SQL_PROFESSION_NAME)),
				new NormalNumber((int)result.get(WebConsts.SQL_ISMEN_NAME)),
				new NormalNumber((int)result.get(WebConsts.SQL_SATISFACTION_NAME)),
				new CommentWord((String)result.get(WebConsts.SQL_COMMENT_NAME)),
				((Timestamp)result.get(WebConsts.SQL_CREATED_NAME)).toLocalDateTime()
				);
		
		return model;
	}
}
