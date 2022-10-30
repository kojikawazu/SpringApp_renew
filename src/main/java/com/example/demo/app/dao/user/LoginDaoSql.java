package com.example.demo.app.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.app.dao.SuperDao;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.user.LoginId;
import com.example.demo.common.id.user.SessionId;
import com.example.demo.common.id.user.UserId;

/**
 * ログインDaoクラス
 * @author nanai
 *
 */
@Component
@Repository
public class LoginDaoSql implements SuperDao<LoginModel, LoginId>, LoginDao {

	/** 
	 * Jdbcドライバー
	 * {@link JdbcTemplate} 
	 */
	private final JdbcTemplate jdbcTemp;
	
	/**
	 * デバッグログ
	 * {@link Logger}
	 */
	private final Logger  logger;
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp {@link JdbcTemplate}
	 */
	public LoginDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp	= jdbcTemp;
		this.logger		= LoggerFactory.getLogger(LoginDaoSql.class);
	}
	
	/**
	 * 追加
	 * @param model {@link LoginModel}
	 */
	@Override
	public void insert(LoginModel model) {
		if (model == null)	return ;
		String sql = "INSERT INTO login_user("
				+ "user_id, session_id, created, updated) "
				+ "VALUES(?,?,?,?)";
		try {
			this.jdbcTemp.update(
					sql,
					model.getUserId(),
					model.getSessionId(),
					model.getCreated(),
					model.getUpdated()
					);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 追加
	 * @param  model {@link LoginModel}
	 * @return {@link LoginId}
	 */
	@Override
	public LoginId insert_returnId(LoginModel model) {
		if (model == null)	new LoginId(0);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "INSERT INTO login_user("
				+ "user_id, session_id, created, updated) "
				+ "VALUES(?,?,?,?)";
		int keyId = 0;
		
		try {
			this.jdbcTemp.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(
						Connection con) 
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(
							sql,
							new String[] { "id" });
					Timestamp createdStamp = Timestamp.valueOf(model.getCreated());
					Timestamp updatedStamp = Timestamp.valueOf(model.getUpdated());
					ps.setInt(1, model.getUserId());
					ps.setString(2, model.getSessionId());
					ps.setTimestamp(3, createdStamp);
					ps.setTimestamp(4, updatedStamp);
					return ps;
				}
			}, keyHolder);
			keyId = keyHolder.getKey().intValue();
		} catch(Exception ex) {
			ex.printStackTrace();
			keyId = 0;
		}
		return new LoginId(keyId);
	}

	/**
	 * 更新
	 * @param  model {@link LoginModel}
	 * @return 0以下 失敗 それ以外 成功 
	 */
	@Override
	public int update(LoginModel model) {
		if (model == null)	return WebConsts.ERROR_NUMBER;
		String sql = "UPDATE login_user SET "
				+ "user_id = ?, session_id = ?, updated = ? "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
					sql,
					model.getUserId(),
					model.getSessionId(),
					model.getUpdated(),
					model.getId()
				);
	}
	
	/**
	 * ログイン情報の更新日付更新
	 * @param  loginId ログインID
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int updateTime(LoginId loginId) {
		if (loginId == null)	return WebConsts.ERROR_NUMBER;
		String sql = "UPDATE login_user SET "
				+ "updated = ? "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
					sql,
					LocalDateTime.now(),
					loginId.getId()
				);
	}

	/**
	 * 削除
	 * @param  id {@link LoginId}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	public int delete(LoginId id) {
		if (id == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM login_user "
				+ "WHERE id = ?";
		
		return this.jdbcTemp.update(
				sql, 
				id.getId());
	}

	/**
	 * 全選択
	 * @return ログインモデルリスト {@link List}({@link LoginModel})
	 */
	@Override
	public List<LoginModel> getAll() {
		String sql = "SELECT id, user_id, session_id, created, updated "
				+ "FROM login_user";
		List<LoginModel> list = new ArrayList<LoginModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			list = this.setLoginModelList(resultList);
		} catch(Exception ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}

	/**
	 * 選択
	 * @param  id			{@link LoginId}
	 * @return ログインモデル	{@link LoginModel}
	 */
	@Override
	public LoginModel select(LoginId id) {
		if (id == null) return null;
		
		LoginModel model = null;
		String sql = "SELECT id, user_id, session_id, created, updated "
				+ "FROM login_user "
				+ "WHERE id = ?";
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					id.getId());
			if (result == null) return null;
			
			model = this.makeLoginModel(result);
		} catch(Exception ex) {
			logger.error("LoginDaoSql#select("
					+ "id) : "
					+ ex.getMessage());
			model = null;
		}
		
		return model;
	}
	
	/**
	 * 選択
	 * @param  userId {@link UserId}
	 * @return {@link LoginModel}
	 */
	@Override
	public LoginModel select(UserId userId) {
		if (userId == null) return null;
		
		LoginModel model = null;
		String sql = "SELECT id, user_id, session_id, created, updated "
				+ "FROM login_user "
				+ "WHERE user_id = ?";
		
		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					userId.getId());
			if (result == null) return null;
			
			model = this.makeLoginModel(result);
		} catch(Exception ex) {
			logger.error("LoginDaoSql#select("
					+ "userId) : "
					+ ex.getMessage());
			model = null;
		}
		
		return model;
	}

	/**
	 * IDは存在する？
	 * @param id
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byId(int targetID) {
		String sql = "SELECT id "
				+ "FROM login_user "
				+ "WHERE id = ?";
		
		boolean isTrue = true;
		try {
			isTrue = jdbcTemp.query(sql, 
				new Object[]{ targetID },
				new int[]{ Types.INTEGER },
				rs -> {
					return rs.next() ? true : false;
				}
			);
		} catch(Exception ex) {
			ex.printStackTrace();
			isTrue = false;
		}
		return isTrue;
	}

/** -------------------------------------------------------------------- */
	
	/**
	 * SQL結果からログインモデルリストを作成
	 * @param  resultList		{@link List}({@link Map}({@link String}, {@link Object}))
	 * @return ユーザーモデルリスト 	{@link List}({@link LoginModel})
	 */
	private List<LoginModel> setLoginModelList(List<Map<String, Object>> resultList){
		List<LoginModel> list = new ArrayList<LoginModel>();
		
		for (Map<String, Object> result : resultList) {
			LoginModel model = this.makeLoginModel(result);
			if (model == null)	continue;
			
			list.add(model);
		}
		
		return list;
	}
	
	/**
	 * モデル生成
	 * @param  result マップ	{@link Map}({@link String}, {@link Object})
	 * @return ログインモデル	{@link LoginModel}
	 */
	private LoginModel makeLoginModel(Map<String, Object> result) {
		if (result == null)	return null;
		LoginModel model = null;
		
		try {
			model = new LoginModel(
				new LoginId((int)result.get(
						WebConsts.SQL_ID_NAME)),
				new UserId((int)result.get(
						WebConsts.SQL_USER_ID_NAME)),
				new SessionId((String)result.get(
						WebConsts.SQL_SESSION_ID_NAME)),
				((Timestamp)result.get(
						WebConsts.SQL_CREATED_NAME)).toLocalDateTime(),
				((Timestamp)result.get(
						WebConsts.SQL_UPDATED_NAME)).toLocalDateTime()
				);
		} catch(Exception ex) {
			model = null;
		}
		
		return model;
	}
}
