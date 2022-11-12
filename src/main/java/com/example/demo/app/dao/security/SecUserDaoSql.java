package com.example.demo.app.dao.security;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.app.dao.SuperDao;
import com.example.demo.app.entity.user.SecUserModel;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.id.user.UserId;
import com.example.demo.common.word.EmailWord;
import com.example.demo.common.word.NameWord;
import com.example.demo.common.word.PasswdWord;

/**
 * セキュリティユーザーDaoクラス
 * @author nanai
 *
 */
@Component
@Repository
@Transactional(readOnly = true)
public class SecUserDaoSql implements SuperDao<SecUserModel, UserId>, SecUserDao {

	/**
	 * DB
	 */
	private final String DB_NAME 			= "security_login_user";
	private final String DB_ROLE_NAME		= "security_roles";
	private final String DB_USER_ROLE_NAME	= "security_user_role";
	
	/**
	 * パラメータ
	 */
	private final String PARAM_ID			= WebConsts.SQL_ID_NAME;
	private final String PARAM_NAME			= WebConsts.SQL_NAME_NAME;
	private final String PARAM_EMAIL		= WebConsts.SQL_EMAIL_NAME;
	private final String PARAM_PASSWORD		= WebConsts.SQL_PASSWORD_NAME;
	private final String PARAM_CREATED		= WebConsts.SQL_CREATED_NAME;
	private final String PARAM_UPDATED		= WebConsts.SQL_UPDATED_NAME;
	private final String PARAM_ROLE_NAME	= WebConsts.SQL_ROLE_NAME;
	private final String PARAM_ROLE_ID		= WebConsts.SQL_ROLE_ID;
	private final String PARAM_USER_ID		= WebConsts.SQL_USER_ID_NAME;
	
	/** ------------------------------------------------------------------------- */
	
	/** 
	 * Jdbcドライバー
	 * {@link JdbcTemplate} 
	 */
	private final JdbcTemplate jdbcTemp;
	
	/** ------------------------------------------------------------------------- */
	
	/**
	 * コンストラクタ
	 * @param jdbcTemp	{@link JdbcTemplate}
	 */
	public SecUserDaoSql(
			JdbcTemplate	jdbcTemp) {
		this.jdbcTemp	= jdbcTemp;
	}
	
	/**
	 * 追加
	 * @param model {@link SecUserModel}
	 */
	@Override
	@Transactional(readOnly = false)
	public void insert(SecUserModel model) {
		if (model == null)	return ;
		String sql = "INSERT INTO " 
				+ DB_NAME 
				+ "("
				+ PARAM_NAME		+ ", " 
				+ PARAM_EMAIL		+ ", " 
				+ PARAM_PASSWORD 	+ ", " 
				+ PARAM_CREATED 	+ ", "
				+ PARAM_UPDATED 
				+ ") "
				+ "VALUES(?,?,?,?,?)";
		try {
			this.jdbcTemp.update(
					sql,
					model.getName(),
					model.getEmail(),
					model.getPassword(),
					model.getCreated(),
					model.getUpdated()
					);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 更新
	 * @param  model {@link SecUserModel}
	 * @return 0以下 失敗 それ以外 成功 
	 */
	@Override
	@Transactional(readOnly = false)
	public int update(SecUserModel model) {
		if (model == null)	return WebConsts.ERROR_NUMBER;
		String sql = "UPDATE " + DB_NAME + " SET "
				+ PARAM_NAME		+ " = ?, "
				+ PARAM_EMAIL		+ " = ?, "
				+ PARAM_PASSWORD	+ " = ?, "
				+ PARAM_UPDATED 	+ " = ? "
				+ "WHERE " + PARAM_ID + " = ?";
		
		return this.jdbcTemp.update(
					sql,
					model.getName(),
					model.getEmail(),
					model.getPassword(),
					model.getUpdated(),
					model.getId()
				);
	}

	/**
	 * 削除
	 * @param  id {@link UserId}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false)
	public int delete(UserId id) {
		if (id == null)	return WebConsts.ERROR_NUMBER;
		String sql = "DELETE FROM " + DB_NAME  + " "
				+ "WHERE " + PARAM_ID + " = ?";
		
		return this.jdbcTemp.update(
				sql, 
				id.getId());
	}

	/**
	 * 全選択
	 * @return ユーザーモデルリスト {@link List}({@link SecUserModel})
	 */
	@Override
	public List<SecUserModel> getAll() {
		String sql = "SELECT "
				+ DB_NAME + "." + PARAM_ID			+ " AS " + PARAM_ID       + ", "
				+ DB_NAME + "." + PARAM_NAME		+ " AS " + PARAM_NAME     + ", " 
				+ DB_NAME + "." + PARAM_EMAIL		+ " AS " + PARAM_EMAIL    + ", " 
				+ DB_NAME + "." + PARAM_PASSWORD 	+ " AS " + PARAM_PASSWORD + ", " 
				+ DB_NAME + "." + PARAM_CREATED 	+ " AS " + PARAM_CREATED  + ", "
				+ DB_NAME + "." + PARAM_UPDATED 	+ " AS " + PARAM_UPDATED  + ", "
				+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " AS " + PARAM_ROLE_NAME + " "
				+ "FROM " + DB_NAME + " "
				+ "JOIN " + DB_USER_ROLE_NAME + " "
				+ "ON " + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
				+ "JOIN " + DB_ROLE_NAME + " "
				+ "ON " + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID;
		List<SecUserModel> list = new ArrayList<SecUserModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			
			list = this.setSecUserModelList(resultList);
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
		}
		
		return list;
	}

	/**
	 * IDによる選択
	 * @param  id {@link UserId}
	 * @return {@link SecUserModel}
	 */
	@Override
	public SecUserModel select(UserId id) {
		if (id == null)	return null;
		
		SecUserModel model = null;
		String sql = "SELECT "
				+ DB_NAME + "." + PARAM_ID			+ " AS " + PARAM_ID       + ", "
				+ DB_NAME + "." + PARAM_NAME		+ " AS " + PARAM_NAME     + ", " 
				+ DB_NAME + "." + PARAM_EMAIL		+ " AS " + PARAM_EMAIL    + ", " 
				+ DB_NAME + "." + PARAM_PASSWORD 	+ " AS " + PARAM_PASSWORD + ", " 
				+ DB_NAME + "." + PARAM_CREATED 	+ " AS " + PARAM_CREATED  + ", "
				+ DB_NAME + "." + PARAM_UPDATED 	+ " AS " + PARAM_UPDATED  + ", "
				+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " AS " + PARAM_ROLE_NAME + " "
				+ "FROM " + DB_NAME + " "
				+ "JOIN " + DB_USER_ROLE_NAME + " "
				+ "ON " + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
				+ "JOIN " + DB_ROLE_NAME + " "
				+ "ON " + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID + " "
				+ "WHERE " + DB_NAME + "." + PARAM_ID + " = ?";
		List<SecUserModel> list = new ArrayList<SecUserModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(
					sql,
					id.getId());
			
			list = this.setSecUserModelList(resultList);
			model = list.get(0);
			list.clear();
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			list.clear();
			model = null;
		}
		
		return model;
	}
	
	/**
	 * Eメールによる選択
	 * @param  email	{@link EmailWord}
	 * @return {@link SecUserModel}
	 */
	@Override
	public SecUserModel select(EmailWord email) {
		if (email == null)	return null;
		
		SecUserModel model = null;
		String sql = "SELECT "
				+ DB_NAME + "." + PARAM_ID			+ " AS " + PARAM_ID       + ", "
				+ DB_NAME + "." + PARAM_NAME		+ " AS " + PARAM_NAME     + ", " 
				+ DB_NAME + "." + PARAM_EMAIL		+ " AS " + PARAM_EMAIL    + ", " 
				+ DB_NAME + "." + PARAM_PASSWORD 	+ " AS " + PARAM_PASSWORD + ", " 
				+ DB_NAME + "." + PARAM_CREATED 	+ " AS " + PARAM_CREATED  + ", "
				+ DB_NAME + "." + PARAM_UPDATED 	+ " AS " + PARAM_UPDATED  + ", "
				+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " AS " + PARAM_ROLE_NAME + " "
				+ "FROM " + DB_NAME + " "
				+ "JOIN " + DB_USER_ROLE_NAME + " "
				+ "ON " + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
				+ "JOIN " + DB_ROLE_NAME + " "
				+ "ON " + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID + " "
				+ "WHERE " + DB_NAME + "." + PARAM_EMAIL    + " = ?";
		List<SecUserModel> list = new ArrayList<SecUserModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(
					sql,
					email.getWord());
			
			list = this.setSecUserModelList(resultList);
			model = list.get(0);
			list.clear();
		} catch(Exception ex) {
			ex.printStackTrace();
			list.clear();
			model = null;
		}
		
		return model;
	}
	
	/**
	 * Eメール、パスワードによる選択
	 * @param  email	{@link EmailWord}
	 * @param  password	{@link PasswdWord}
	 * @return {@link SecUserModel}
	 */
	@Override
	public SecUserModel select(EmailWord email, PasswdWord password) {
		if (email == null || password == null)	return null;
		
		SecUserModel model = null;
		String sql = "SELECT "
				+ DB_NAME + "." + PARAM_ID			+ " AS " + PARAM_ID       + ", "
				+ DB_NAME + "." + PARAM_NAME		+ " AS " + PARAM_NAME     + ", " 
				+ DB_NAME + "." + PARAM_EMAIL		+ " AS " + PARAM_EMAIL    + ", " 
				+ DB_NAME + "." + PARAM_PASSWORD 	+ " AS " + PARAM_PASSWORD + ", " 
				+ DB_NAME + "." + PARAM_CREATED 	+ " AS " + PARAM_CREATED  + ", "
				+ DB_NAME + "." + PARAM_UPDATED 	+ " AS " + PARAM_UPDATED  + ", "
				+ DB_ROLE_NAME + "."+ PARAM_NAME 	+ " AS " + PARAM_ROLE_NAME + " "
				+ "FROM " + DB_NAME + " "
				+ "JOIN " + DB_USER_ROLE_NAME + " "
				+ "ON " + DB_NAME + "." + PARAM_ID + " = " + DB_USER_ROLE_NAME + "." + PARAM_USER_ID + " "
				+ "JOIN " + DB_ROLE_NAME + " "
				+ "ON " + DB_USER_ROLE_NAME + "." + PARAM_ROLE_ID + " = " + DB_ROLE_NAME + "." + PARAM_ID + " "
				+ "WHERE " + DB_NAME + "." + PARAM_EMAIL    + " = ? and "
				+            DB_NAME + "." + PARAM_PASSWORD + " = ?";
		List<SecUserModel> list = new ArrayList<SecUserModel>();
		
		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(
					sql,
					email.getWord(),
					password.getWord());
			
			list = this.setSecUserModelList(resultList);
			model = list.get(0);
			list.clear();
		} catch(Exception ex) {
			ex.printStackTrace();
			list.clear();
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
		String sql = "SELECT " + PARAM_ID + " "
				+ "FROM " + DB_NAME + " "
				+ "WHERE " + PARAM_ID + " = ?";
		
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
	 * SQL結果からセキュリティユーザーモデルリストを作成
	 * @param  resultList				{@link List}({@link Map}({@link String}, {@link Object}))
	 * @return セキュリティユーザーモデルリスト 	{@link List}({@link SecUserModel})
	 */
	private List<SecUserModel> setSecUserModelList(List<Map<String, Object>> resultList){
		List<SecUserModel> list = new ArrayList<SecUserModel>();
		
		for (int idx=0, len=resultList.size(); idx<len; idx++) {
			Map<String, Object> result 	= resultList.get(idx);
			SecUserModel 		model 	= this.makeSecUserModel(result);
			if (model == null)		continue;
			list.add(model);
			
			String role = this.makeRole(result);
			if (role.equals(""))	continue;
			
			List<String> roleList = model.getRoleList();
			roleList.add(role);
			int skipCnt = this.setRoleList(resultList, idx, model);
			idx += skipCnt;
		}
		
		return list;
	}
	
	/**
	 * ロールリストの設定
	 * @param resultList {@link List}({@link Map}({@link String}, {@link Object}))
	 * @param idx
	 * @param model {@link SecUserModel}
	 * @return skipCnt
	 */
	private int setRoleList(
			List<Map<String, Object>> resultList,
			int                       idx,
			SecUserModel             model) {
		List<String> 	roleList 	= model.getRoleList();
		int				userMainId	= model.getId();
		int				skipCnt		= 0;
		
		for (int roleIdx=1, len=resultList.size(); idx+roleIdx<len; roleIdx++) {
			Map<String, Object>	resultRole	= resultList.get(idx+roleIdx);
			int					userDiffId	= (int)resultRole.get(WebConsts.SQL_ID_NAME);
			
			// 違うIDだったら終了
			if (userMainId != userDiffId)	break;
			
			String role	= this.makeRole(resultRole);
			// ロールなかったら終了
			if (role.equals(""))			break;
			roleList.add(role);
			skipCnt++;
		}
		
		return skipCnt;
	}
	
	/**
	 * モデル生成
	 * @param  result マップ	{@link Map}({@link String}, {@link Object})
	 * @return ユーザーモデル	{@link SecUserModel}
	 */
	private SecUserModel makeSecUserModel(Map<String, Object> result) {
		if (result == null)	return null;
		SecUserModel model = null;
		
		try {
			
			model = new SecUserModel(
				new UserId((int)result.get(
						WebConsts.SQL_ID_NAME)),
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
			
		} catch(Exception ex) {
			model = null;
		}
		
		return model;
	}
	
	/**
	 * ロール生成
	 * @param  result マップ	{@link Map}({@link String}, {@link Object})
	 * @return ロール名
	 */
	private String makeRole(Map<String, Object> result) {
		if (result == null)	return "";
		String roleWord = "";
		
		try {
			roleWord = (String)result.get(
					WebConsts.SQL_ROLE_NAME);
		} catch(NullPointerException ex) {
			roleWord = "";
		}
		
		return roleWord;
	}

}
