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

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.app.common.id.user.LoginId;
import com.example.demo.app.common.id.user.SessionId;
import com.example.demo.app.common.id.user.UserId;
import com.example.demo.app.dao.SuperDao;
import com.example.demo.app.entity.user.LoginModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;

/**
 * ログインDaoクラス
 * @author nanai
 *
 */
@Component
@Repository
@Transactional(readOnly = true)
public class LoginDaoSql implements SuperDao<LoginModel, LoginId>, LoginDao {

	/**
	 * DB名
	 */
	private final String DB_NAME = "login_user";

	/**
	 * パラム名
	 */
	private final String PARAM_ID 			= "id";
	private final String PARAM_USER_ID 		= "user_id";
	private final String PARAM_SESSION_ID	= "session_id";
	private final String PARAM_CREATED		= "created";
	private final String PARAM_UPDATED		= "updated";

	/** 
	 * Jdbcドライバー
	 * {@link JdbcTemplate} 
	 */
	private final JdbcTemplate jdbcTemp;

	/**
	 * デバッグログ
	 * {@link IntroAppLogWriter}
	 */
	private final IntroAppLogWriter  logWriter;

	/** ------------------------------------------------------------------------------------- */

	/**
	 * コンストラクタ
	 * @param jdbcTemp {@link JdbcTemplate}
	 */
	public LoginDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp	= jdbcTemp;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 追加
	 * @param model {@link LoginModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insert(LoginModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_INSERT + " " + DB_NAME
				+ "("
				+ PARAM_USER_ID    + ", "
				+ PARAM_SESSION_ID + ", "
				+ PARAM_CREATED    + ", "
				+ PARAM_UPDATED
				+ ") "
				+ WebConsts.SQL_VALUES + "(?,?,?,?)";

		synchronized (LoginDaoSql.class) {
			this.jdbcTemp.update(
					sql,
					model.getUserId(),
					model.getSessionId(),
					model.getCreated(),
					model.getUpdated()
					);
		}
	}

	/**
	 * 追加
	 * @param  model {@link LoginModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return {@link LoginId}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public LoginId insert_returnId(LoginModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = WebConsts.SQL_INSERT + " " + DB_NAME
				+ "("
				+ PARAM_USER_ID    + ", "
				+ PARAM_SESSION_ID + ", "
				+ PARAM_CREATED    + ", "
				+ PARAM_UPDATED
				+ ") "
				+ WebConsts.SQL_VALUES + "(?,?,?,?)";
		int keyId = 0;

		synchronized (LoginDaoSql.class) {
			this.jdbcTemp.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(
						Connection con) 
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(
							sql,
							new String[] { PARAM_ID });
					Timestamp createdStamp = Timestamp.valueOf(model.getCreated());
					Timestamp updatedStamp = Timestamp.valueOf(model.getUpdated());
					ps.setInt(1, model.getUserId());
					ps.setString(2, model.getSessionId());
					ps.setTimestamp(3, createdStamp);
					ps.setTimestamp(4, updatedStamp);
					return ps;
				}
			}, keyHolder);
		}
		keyId = keyHolder.getKey().intValue();

		return new LoginId(keyId);
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 更新
	 * @param  model {@link LoginModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功 
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int update(LoginModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_USER_ID    + " = ?, "
				+ PARAM_SESSION_ID + " = ?, "
				+ PARAM_UPDATED    + " = ? "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";
		
		synchronized (LoginDaoSql.class) {
			result = this.jdbcTemp.update(
					sql,
					model.getUserId(),
					model.getSessionId(),
					model.getUpdated(),
					model.getId()
				);
		}

		return result;
	}

	/**
	 * ログイン情報の更新日付更新
	 * @param  loginId ログインID {@link LoginId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int updateTime(LoginId loginId) {
		if (loginId == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = "UPDATE " + DB_NAME + " SET "
				+ PARAM_UPDATED + " = ? "
				+ "WHERE " + PARAM_ID + " = ?";

		synchronized (LoginDaoSql.class) {
			result = this.jdbcTemp.update(
					sql,
					LocalDateTime.now(),
					loginId.getId()
				);
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param  id {@link LoginId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delete(LoginId id) {
		if (id == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ "WHERE " + PARAM_ID + " = ?";

		synchronized (LoginDaoSql.class) {
			result = this.jdbcTemp.update(
					sql, 
					id.getId()
				);
		}

		return result;
	}

	/**
	 * ユーザIDによる削除
	 * @param  userId {@link UserId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int delete_byUserId(UserId userId) {
		if (userId == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ "WHERE " + PARAM_USER_ID + " = ?";

		synchronized (LoginDaoSql.class) {
			result = this.jdbcTemp.update(
					sql, 
					userId.getId()
				);
		}

		return result;
	}

	/**
	 * 全削除
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int deleteAll() {
		String sql = WebConsts.SQL_DELETE + " " + DB_NAME;
		int result = 0;

		synchronized (LoginDaoSql.class) {
			result = this.jdbcTemp.update(
					sql);
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 全選択
	 * @return ログインモデルリスト {@link List}({@link LoginModel})
	 */
	@Override
	public List<LoginModel> getAll() {
		String sql = "SELECT " + PARAM_ID + ", "
				+ PARAM_USER_ID    + ", "
				+ PARAM_SESSION_ID + ", "
				+ PARAM_CREATED    + ", "
				+ PARAM_UPDATED    + " "
				+ "FROM " + DB_NAME;
		List<LoginModel> list = new ArrayList<LoginModel>();

		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);

			list = this.setLoginModelList(resultList);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
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
		String sql = "SELECT " + PARAM_ID + ", "
				+ PARAM_USER_ID    + ", "
				+ PARAM_SESSION_ID + ", "
				+ PARAM_CREATED    + ", "
				+ PARAM_UPDATED    + " "
				+ "FROM " + DB_NAME + " "
				+ "WHERE " + PARAM_ID + " = ?";

		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					id.getId());
			if (result == null) return null;

			model = this.makeLoginModel(result);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
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
		String sql = "SELECT " + PARAM_ID + ", "
				+ PARAM_USER_ID    + ", "
				+ PARAM_SESSION_ID + ", "
				+ PARAM_CREATED    + ", "
				+ PARAM_UPDATED    + " "
				+ "FROM " + DB_NAME + " "
				+ "WHERE " + PARAM_USER_ID + " = ?";

		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(
					sql, 
					userId.getId());
			if (result == null) return null;

			model = this.makeLoginModel(result);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			model = null;
		}

		return model;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * IDは存在する？
	 * @param id
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byId(int targetID) {
		String sql = "SELECT " + PARAM_ID + " "
				+ "FROM " + DB_NAME + " "
				+ "WHERE " + PARAM_ID + " = ?";

		boolean isTrue = true;
		try {
			isTrue = this.jdbcTemp.query(sql, 
				new Object[]{ targetID },
				new int[]{ Types.INTEGER },
				rs -> {
					return rs.next() ? true : false;
				}
			);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			isTrue = false;
		}
		return isTrue;
	}

	/**
	 * IDは存在する？
	 * @param id {@link UserId}
	 * @return true 存在する false 存在しない
	 */
	@Override
	public boolean isSelect_byUserId(UserId targetID) {
		String sql = "SELECT " + PARAM_ID + " "
				+ "FROM " + DB_NAME + " "
				+ "WHERE " + PARAM_USER_ID + " = ?";

		boolean isTrue = true;
		try {
			isTrue = this.jdbcTemp.query(sql, 
				new Object[]{ targetID.getId() },
				new int[]{ Types.INTEGER },
				rs -> {
					return rs.next() ? true : false;
				}
			);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
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
	private List<LoginModel> setLoginModelList(List<Map<String, Object>> resultList) {
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
