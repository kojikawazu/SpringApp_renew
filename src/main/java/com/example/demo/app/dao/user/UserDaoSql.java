package com.example.demo.app.dao.user;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.app.dao.SuperDao;
import com.example.demo.app.entity.user.UserModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.user.UserId;
import com.example.demo.common.id.user.UserKindId;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * ユーザーDaoクラス
 * @author nanai
 *
 */
@Component
@Repository
public class UserDaoSql implements SuperDao<UserModel, UserId> {
	
	/** Jdbcドライバー */
	private final JdbcTemplate jdbcTemp;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp
	 */
	public UserDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

	/**
	 * 追加
	 * @param model
	 */
	@Override
	public void insert(UserModel model) {
		if(model == null)	return ;
		String sql = "INSERT INTO home_user("
				+ "kind_id, name, email, password, created, updated) "
				+ "VALUES(?,?,?,?,?,?)";
		try {
			this.jdbcTemp.update(
					sql,
					model.getKindId(),
					model.getName(),
					model.getEmail(),
					model.getPassword(),
					model.getCreated(),
					model.getUpdated()
					);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 更新
	 * @param  model
	 * @return 0以下 失敗 それ以外 成功 
	 */
	@Override
	public int update(UserModel model) {
		if(model == null)	return WebConsts.ERROR_NUMBER;
		String sql = "UPDATE home_user SET "
				+ "kind_id = ?, name = ?, email = ?, password = ?, updated = ? "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
					sql,
					model.getKindId(),
					model.getName(),
					model.getEmail(),
					model.getPassword(),
					model.getUpdated(),
					model.getId()
				);
	}

	/**
	 * 削除
	 * @param  id
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int delete(UserId id) {
		if(id == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM home_user "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
				sql, 
				id.getId());
	}

	/**
	 * 全選択
	 * @return ユーザーモデルリスト
	 */
	@Override
	public List<UserModel> getAll() {
		String sql = "SELECT id, kind_id, name, email, password, created, updated FROM home_user";
		List<UserModel> list = new ArrayList<UserModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			list = this.setUserModelList(resultList);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}

	/**
	 * 選択
	 * @param  id
	 * @return ユーザーモデル
	 */
	@Override
	public UserModel select(UserId id) {
		if(id == null) return null;
		
		UserModel model = null;
		String sql = "SELECT * "
				+ "FROM home_user "
				+ "WHERE id = ?";
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, id.getId());
			if(result == null) return null;
			
			model = this.makeUserModel(result);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			model = null;
		}
		
		return model;
	}
	
	/**
	 * SQL結果からユーザーモデルリストを作成
	 * @param  resultList
	 * @return ユーザーモデルリスト
	 */
	private List<UserModel> setUserModelList(List<Map<String, Object>> resultList){
		List<UserModel> list = new ArrayList<UserModel>();
		
		for ( Map<String, Object> result : resultList ) {
			UserModel model = this.makeUserModel(result);
			if(model == null)	continue;
			
			list.add(model);
		}
		
		return list;
	}

	/**
	 * モデル生成
	 * @param  result マップ
	 * @return ユーザーモデル
	 */
	private UserModel makeUserModel(Map<String, Object> result) {
		if(result == null)	return null;
		UserModel model = null;
		
		try {
			model = new UserModel(
				new UserId((int)result.get(
						WebConsts.SQL_ID_NAME)),
				new UserKindId((int)result.get(
						WebConsts.SQL_KIND_ID_NAME)),
				new NameWord((String)result.get(
						WebConsts.SQL_NAME_NAME)),
				new EmailWord((String)result.get(
						WebConsts.SQL_EMAIL_NAME)),
				new PasswdWord((String)result.get(
						WebConsts.SQL_PASSWORD_NAME)),
				((Timestamp)result.get(
						WebConsts.SQL_CREATED_NAME)).toLocalDateTime(),
				((Timestamp)result.get(
						WebConsts.SQL_UPDATED_NAME)).toLocalDateTime()
				);
		} catch(NullPointerException ex) {
			model = null;
		}
		
		return model;
	}

}
