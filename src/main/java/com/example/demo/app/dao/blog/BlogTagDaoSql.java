package com.example.demo.app.dao.blog;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.dao.SuperDao;
import com.example.demo.app.entity.blog.BlogTagModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.common.WebConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.word.TagWord;

/**
 * ブログタグDaoクラス
 * <br>
 * implements {@link SuperDao}<{@link BlogTagModel}, {@link BlogTagId}><br>
 *            {@link BlogTagDao}
 * @author nanai
 *
 */
@Repository
@Transactional(readOnly = true)
public class BlogTagDaoSql implements SuperDao<BlogTagModel, BlogTagId>, BlogTagDao {

	/** DB名 */
	private final String DB_NAME = "blog_tag";

	/** パラム名 */
	private final String PARAM_ID			= "id";
	private final String PARAM_TAG			= "tag";

	/** 
	 * Jdbcドライバー 
	 * {@link JdbcTemplate}
	 */
	private JdbcTemplate jdbcTemp;

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
	@Autowired
	public BlogTagDaoSql(JdbcTemplate jdbcTemp) {
		this.jdbcTemp 	= jdbcTemp;
		this.logWriter	= IntroAppLogWriter.getInstance();
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 追加
	 * @param model {@link BlogTagModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void insert(BlogTagModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_INSERT + " " + DB_NAME
				+ "(" + PARAM_TAG +  ") "
				+ WebConsts.SQL_VALUES + "(?)";

		synchronized (BlogTagDaoSql.class) {
			this.jdbcTemp.update(
					sql,
					model.getTag());
		}
	}

	/**
	 * 追加
	 * @param model {@link BlogTagModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return id
	 */
	@Override
	public int insert_returnId(BlogTagModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_INSERT + " " + DB_NAME
				+ "(" + PARAM_TAG +  ") "
				+ WebConsts.SQL_VALUES + "(?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int keyId = 0;

		synchronized (BlogTagDaoSql.class) {
			this.jdbcTemp.update(
				conn -> {
					PreparedStatement ps = conn.prepareStatement(
						sql,
						new String[] { PARAM_ID });
					ps.setString(1, model.getTag());
					return ps;
				}, keyHolder);
			keyId = keyHolder.getKey().intValue();
		}

		return keyId;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 更新
	 * @param model {@link BlogTagModel}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public int update(BlogTagModel model) {
		if (model == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql = WebConsts.SQL_UPDATE + " " + DB_NAME + " " + WebConsts.SQL_SET + " "
				+ PARAM_TAG + " = ? "
				+ WebConsts.SQL_WHERE + " "+ PARAM_ID + " = ?";

		synchronized (BlogTagDaoSql.class) {
			result = this.jdbcTemp.update(
					sql,
					model.getTag(),
					model.getId());
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 削除
	 * @param id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return 0以下 失敗 それ以外 成功 
	 */
	@Override
	public int delete(BlogTagId id) {
		if (id == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		int result = 0;
		String sql =  WebConsts.SQL_DELETE + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";

		synchronized (BlogTagDaoSql.class) {
			result = this.jdbcTemp.update(
					sql, 
					id.getId());
		}

		return result;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 全て選択
	 * @return ブログタグモデルリスト {@link List}<{@link BlogTagModel}>
	 */
	@Override
	public List<BlogTagModel> getAll() {
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_ID  + ", "
				+ PARAM_TAG + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME;
		List<BlogTagModel> list = new ArrayList<BlogTagModel>();

		try {
			List<Map<String, Object>> resultList = this.jdbcTemp.queryForList(sql);
			if (resultList == null)	return list;

			for (Map<String, Object> result : resultList) {
				BlogTagModel model = this.makeModel(result);
				if(model == null)	continue;
				list.add(model);
			}
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			list.clear();
		}

		return list;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * 選択
	 * @param id {@link BlogTagId}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return ブログタグモデル {@link BlogTagModel}
	 */
	@Override
	public BlogTagModel select(BlogTagId id) {
		if (id == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_SELECT + " "
				+ PARAM_ID  + ", "
				+ PARAM_TAG + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_ID + " = ?";
		BlogTagModel model = null;

		try {
			Map<String, Object> result = this.jdbcTemp.queryForMap(sql, id.getId());
			if (result == null) return null;
			model = this.makeModel(result);
		} catch(Exception ex) {
			this.logWriter.error(ex.getMessage());
			model = null;
		}

		return model;
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * タグが存在するかチェック
	 * @param target {@link String}
	 * @throws {@link WebMvcConfig#ARGUMENTS_ERROR()}
	 * @return true タグ存在する false タグ存在しない
	 */
	@Override
	public boolean isSelect_byTag(String target) {
		if (target == null)	throw WebMvcConfig.ARGUMENTS_ERROR();
		String sql = WebConsts.SQL_SELECT + " " 
				+ PARAM_ID + " "
				+ WebConsts.SQL_FROM + " " + DB_NAME + " "
				+ WebConsts.SQL_WHERE + " " + PARAM_TAG + " = ?";

		return this.jdbcTemp.query(sql, 
				new Object[]{ target },
				new int[] { Types.VARCHAR },
				rs -> {
			return rs.next() ? true : false;
		});
	}

	/** ------------------------------------------------------------------------------------- */

	/**
	 * モデル生成
	 * @param  result {@link Map}<{@link String}, {@link Object}>
	 * @return ブログタグモデル {@link BlogTagModel}
	 */
	private BlogTagModel makeModel(Map<String, Object> result) {
		if(result == null)	return null;

		BlogTagModel model =  new BlogTagModel(
				new BlogTagId((int)result.get(PARAM_ID)),
				new TagWord((String)result.get(PARAM_TAG))
				);

		return model;
	}

	/** ------------------------------------------------------------------------------------- */

	@Override
	public boolean isSelect_byId(int targetID) {
		return false;
	}
}
