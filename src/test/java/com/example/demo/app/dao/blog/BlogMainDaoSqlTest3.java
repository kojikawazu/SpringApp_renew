package com.example.demo.app.dao.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.app.common.id.blog.BlogId;
import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.entity.blog.BlogReplyModel;
import com.example.demo.common.consts.TestConsts;
import com.example.demo.common.log.IntroAppLogWriter;
import com.example.demo.common.number.ThanksCntNumber;
import com.example.demo.common.word.CommentWord;
import com.example.demo.common.word.TagWord;
import com.example.demo.common.word.TittleWord;

/**
 * ブログメインDaoクラステスト3
 * @author nanai
 *
 */
public class BlogMainDaoSqlTest3 {

	/**
	 * パラム名
	 */
	private final String PARAM_ID 			= "id";
	private final String PARAM_TITLE 		= "title";
	private final String PARAM_TAG			= "tag";
	private final String PARAM_COMMENT		= "comment";
	private final String PARAM_THANKSCNT	= "thanksCnt";
	private final String PARAM_CREATED		= "created";
	private final String PARAM_UPDATED		= "updated";

	private final String PARAM_REPLY_ID				= "id";
	private final String PARAM_AS_REPLY_ID			= "reply_id";
	private final String PARAM_AS_REPLY_NAME		= "reply_name";
	private final String PARAM_AS_REPLY_COMMENT		= "reply_comment";
	private final String PARAM_AS_REPLY_THANKSCNT	= "reply_thanksCnt";
	private final String PARAM_AS_REPLY_CREATED		= "reply_created";

	/**
	 * 文字列
	 */
	private final String        TEST_REPLY_NAME     = "リプライネーム";
	private final String        TEST_REPLY_COMMENT  = "リプライコメント";

	/** daoクラス */
	private BlogMainDaoSql dao = null;
	/** jdbcクラス */
	@Mock
	JdbcTemplate jdbcTemp = null;
	/** ログクラス  */
	@Mock
	IntroAppLogWriter logWriter = null;

	// --------------------------------------------------------------------------------------------------

	/**
	 * Mockの設定
	 */
	private void setMock() {
		this.jdbcTemp  = mock(JdbcTemplate.class);
		this.logWriter = mock(IntroAppLogWriter.class);
	}

	/**
	 * daoクラスの設定
	 */
	private void setDao() {
		this.dao = new BlogMainDaoSql(this.jdbcTemp);

		Field fld;
		try {
			fld    = this.dao.getClass().getDeclaredField("logWriter");
			fld.setAccessible(true);
			fld.set(this.dao, this.logWriter);
		} catch (IllegalArgumentException | 
				IllegalAccessException | 
				NoSuchFieldException | 
				SecurityException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 初期化
	 */
	@BeforeEach
	public void Init() {
		this.setMock();
		this.setDao();
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setBlogMainModelListテストの準備
	 */
	private Method initSetBlogMainModelList() {
		Method method = null;
		try {
			method = this.dao.getClass().getDeclaredMethod("setBlogMainModelList", List.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return method;
	}
	
	/**
	 * setBlogMainModelListテスト
	 */
	@Test
	public void setBlogMainModelListTest() {
		Method method = initSetBlogMainModelList();

		try {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			Map<String, Object>       map     = new HashMap<String, Object>();
			map.put(PARAM_ID,        1);
			map.put(PARAM_TITLE,     TestConsts.TEST_TITLE_NAME);
			map.put(PARAM_TAG,       TestConsts.TEST_TAG_NAME);
			map.put(PARAM_COMMENT,   TestConsts.TEST_COMMENT_NAME);
			map.put(PARAM_THANKSCNT, 1);
			map.put(PARAM_CREATED,   Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map.put(PARAM_UPDATED,   Timestamp.valueOf(TestConsts.TEST_TIME_02));
			mapList.add(map);

			@SuppressWarnings("unchecked")
			List<BlogMainModel> resultList = (List<BlogMainModel>)method.invoke(this.dao, mapList);

			assertNotNull(resultList);
			assertEquals(1, 							resultList.size());
			assertEquals(1, 							resultList.get(0).getId());
			assertEquals(TestConsts.TEST_TITLE_NAME, 	resultList.get(0).getTitle());
			assertEquals(TestConsts.TEST_TAG_NAME, 		resultList.get(0).getTag());
			assertEquals(TestConsts.TEST_COMMENT_NAME, 	resultList.get(0).getComment());
			assertEquals(1, 							resultList.get(0).getThanksCnt());
			assertEquals(TestConsts.TEST_TIME_01.toString(),
					resultList.get(0).getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(),
					resultList.get(0).getUpdated().toString());
			
			mapList.clear();
			resultList.clear();
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setBlogMainModelListテスト(空)
	 */
	@Test
	public void setBlogMainModelListTest_Empty() {
		Method method = initSetBlogMainModelList();

		try {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			@SuppressWarnings("unchecked")
			List<BlogMainModel> resultList = (List<BlogMainModel>)method.invoke(this.dao, mapList);

			assertNotNull(resultList);
			assertEquals(0, resultList.size());
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setBlogMainModelListテスト(引数エラー)
	 */
	@Test
	public void setBlogMainModelListTest_ArgumentsError() {
		Method method = initSetBlogMainModelList();

		try {
			@SuppressWarnings("unchecked")
			List<BlogMainModel> resultList = (List<BlogMainModel>)method.invoke(this.dao, (List<Map<String, Object>>)null);

			assertNotNull(resultList);
			assertEquals(0, resultList.size());
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setBlogMainModelListPlusテストの準備
	 */
	private Method initSetBlogMainModelListPlus() {
		Method method = null;

		try {
			method = this.dao.getClass().getDeclaredMethod("setBlogMainModelListPlus", List.class, boolean.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return method;
	}

	/**
	 * setBlogMainModelListPlusテスト
	 */
	@Test
	public void setBlogMainModelListPlusTest() {
		Method method = initSetBlogMainModelListPlus();

		try {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int cnter=1;
			for (int idx=1; idx<=2; idx++) {
				Map<String, Object> map01 = new HashMap<String, Object>();
				map01.put(PARAM_ID,               cnter);
				map01.put(PARAM_TITLE,            TestConsts.TEST_TITLE_NAME   + cnter);
				map01.put(PARAM_TAG,              TestConsts.TEST_TAG_NAME     + cnter);
				map01.put(PARAM_COMMENT,          TestConsts.TEST_COMMENT_NAME + cnter);
				map01.put(PARAM_THANKSCNT,        cnter);
				map01.put(PARAM_CREATED,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
				map01.put(PARAM_UPDATED,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
				map01.put(PARAM_AS_REPLY_ID,         idx);
				map01.put(PARAM_AS_REPLY_NAME,       TEST_REPLY_NAME    + idx);
				map01.put(PARAM_AS_REPLY_COMMENT,    TEST_REPLY_COMMENT + idx);
				map01.put(PARAM_AS_REPLY_THANKSCNT, 1);
				map01.put(PARAM_AS_REPLY_CREATED,    Timestamp.valueOf(TestConsts.TEST_TIME_01));
				mapList.add(map01);
			}

			cnter++;
			Map<String, Object> map02 = new HashMap<String, Object>();
			map02.put(PARAM_ID,               2);
			map02.put(PARAM_TITLE,            TestConsts.TEST_TITLE_NAME + cnter);
			map02.put(PARAM_TAG,              TestConsts.TEST_TAG_NAME   + cnter);
			map02.put(PARAM_COMMENT,          TestConsts.TEST_COMMENT_NAME + cnter);
			map02.put(PARAM_THANKSCNT,        1);
			map02.put(PARAM_CREATED,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map02.put(PARAM_UPDATED,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
			mapList.add(map02);

			@SuppressWarnings("unchecked")
			List<BlogMainModel> resultList = (List<BlogMainModel>)method.invoke(this.dao, mapList, false);

			assertNotNull(resultList);
			assertEquals(2, resultList.size());

			BlogMainModel model1                 = resultList.get(0);
			List<BlogReplyModel> replyModel1     = model1.getReplyList();
			BlogMainModel model2                 = resultList.get(1);
			List<BlogReplyModel> replyModel2     = model2.getReplyList();

			// model1
			int modelCnt = 1;
			assertEquals(modelCnt, model1.getId());
			assertEquals(TestConsts.TEST_TITLE_NAME   + modelCnt, model1.getTitle());
			assertEquals(TestConsts.TEST_TAG_NAME     + modelCnt, model1.getTag());
			assertEquals(TestConsts.TEST_COMMENT_NAME + modelCnt, model1.getComment());
			assertEquals(1, model1.getThanksCnt());
			assertEquals(TestConsts.TEST_TIME_01.toString(), 
					model1.getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(),
					model1.getUpdated().toString());

			// replyModel
			assertEquals(2, replyModel1.size());
			int modelReplyCnt = 1;
			for (int idx=0; idx<2; idx++) {
				assertEquals(idx+1, 								replyModel1.get(idx).getId());
				assertEquals(modelCnt, 								replyModel1.get(idx).getBlogId());
				assertEquals(TEST_REPLY_NAME    + modelReplyCnt, 	replyModel1.get(idx).getName());
				assertEquals(TEST_REPLY_COMMENT + modelReplyCnt, 	replyModel1.get(idx).getComment());
				assertEquals(1, 									replyModel1.get(idx).getThanksCnt());
				assertEquals(TestConsts.TEST_TIME_01.toString(),
						replyModel1.get(idx).getCreated().toString());
				modelReplyCnt++;
			}

			// model2
			modelCnt++;
			assertEquals(modelCnt, model2.getId());
			assertEquals(TestConsts.TEST_TITLE_NAME   + modelCnt, model2.getTitle());
			assertEquals(TestConsts.TEST_TAG_NAME     + modelCnt, model2.getTag());
			assertEquals(TestConsts.TEST_COMMENT_NAME + modelCnt, model2.getComment());
			assertEquals(1, model2.getThanksCnt());
			assertEquals(TestConsts.TEST_TIME_01.toString(),
					model2.getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(),
					model2.getUpdated().toString());
			assertEquals(0, replyModel2.size());

			mapList.clear();
			resultList.clear();
			replyModel1.clear();
			replyModel2.clear();
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setBlogMainModelListPlusテスト(空)
	 */
	@Test
	public void setBlogMainModelListPlusTest_Empty() {
		Method method = initSetBlogMainModelListPlus();

		try {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			@SuppressWarnings("unchecked")
			List<BlogMainModel> resultList = (List<BlogMainModel>)method.invoke(this.dao, mapList, false);

			assertNotNull(resultList);
			assertEquals(0, resultList.size());
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setBlogMainModelListPlusテスト(引数エラー)
	 */
	@Test
	public void setBlogMainModelListPlusTest_ArgumentsError() {
		Method method = initSetBlogMainModelListPlus();

		try {
			@SuppressWarnings("unchecked")
			List<BlogMainModel> resultList = (List<BlogMainModel>)method.invoke(this.dao, (List<Map<String, Object>>)null, false);

			assertNotNull(resultList);
			assertEquals(0, resultList.size());
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * setBlogReplyModelListテストの準備
	 */
	private Method initSetBlogReplyModelList() {
		Method method = null;

		try {
			method = this.dao.getClass().getDeclaredMethod("setBlogReplyModelList", List.class, int.class, BlogMainModel.class, boolean.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return method;
	}

	/**
	 * setBlogReplyModelListテスト
	 */
	@Test
	public void setBlogReplyModelListTest() {
		Method method = initSetBlogReplyModelList();

		try {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			int cnter=1;
			for (int idx=1; idx<=4; idx++) {
				Map<String, Object> map01 = new HashMap<String, Object>();
				map01.put(PARAM_ID,               cnter);
				map01.put(PARAM_TITLE,            TestConsts.TEST_TITLE_NAME   + cnter);
				map01.put(PARAM_TAG,              TestConsts.TEST_TAG_NAME     + cnter);
				map01.put(PARAM_COMMENT,          TestConsts.TEST_COMMENT_NAME + cnter);
				map01.put(PARAM_THANKSCNT,        cnter);
				map01.put(PARAM_CREATED,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
				map01.put(PARAM_UPDATED,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
				map01.put(PARAM_AS_REPLY_ID,         idx);
				map01.put(PARAM_AS_REPLY_NAME,       TEST_REPLY_NAME    + idx);
				map01.put(PARAM_AS_REPLY_COMMENT,    TEST_REPLY_COMMENT + idx);
				map01.put(PARAM_AS_REPLY_THANKSCNT,  1);
				map01.put(PARAM_AS_REPLY_CREATED,    Timestamp.valueOf(TestConsts.TEST_TIME_01));
				mapList.add(map01);
			}

			cnter++;
			Map<String, Object> map02 = new HashMap<String, Object>();
			map02.put(PARAM_ID,               2);
			map02.put(PARAM_TITLE,            TestConsts.TEST_TITLE_NAME + cnter);
			map02.put(PARAM_TAG,              TestConsts.TEST_TAG_NAME   + cnter);
			map02.put(PARAM_COMMENT,          TestConsts.TEST_COMMENT_NAME + cnter);
			map02.put(PARAM_THANKSCNT,        1);
			map02.put(PARAM_CREATED,          Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map02.put(PARAM_UPDATED,          Timestamp.valueOf(TestConsts.TEST_TIME_02));
			mapList.add(map02);

			BlogMainModel model = new BlogMainModel(
					new BlogId(1),
					new TittleWord(TestConsts.TEST_TITLE_NAME + "1"),
					new TagWord(TestConsts.TEST_TAG_NAME + "1"),
					new CommentWord(TestConsts.TEST_COMMENT_NAME + "1"),
					new ThanksCntNumber(1),
					TestConsts.TEST_TIME_01,
					TestConsts.TEST_TIME_02);

			int skipCnt = (int)method.invoke(this.dao, mapList, 0, model, true);

			assertNotNull(skipCnt);
			assertEquals(3, skipCnt);
			assertEquals(3, model.getReplyList().size());
			for (int i=0; i<3; i++) {
				assertEquals((4-i), model.getReplyList().get(i).getId());
				assertEquals(1,   model.getReplyList().get(i).getBlogId());
				assertEquals(TEST_REPLY_NAME + (4-i), model.getReplyList().get(i).getName());
				assertEquals(TEST_REPLY_COMMENT + (4-i), model.getReplyList().get(i).getComment());
				assertEquals(1, model.getReplyList().get(i).getThanksCnt());
				assertEquals(TestConsts.TEST_TIME_01.toString(), 
						model.getReplyList().get(i).getCreated().toString());
			}
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setBlogReplyModelListテスト(空)
	 */
	@Test
	public void setBlogReplyModelListTest_Empty() {
		Method method = initSetBlogReplyModelList();

		try {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			BlogMainModel model = new BlogMainModel();

			int skipCnt = (int)method.invoke(this.dao, mapList, 0, model, true);

			assertNotNull(skipCnt);
			assertEquals(0, skipCnt);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * setBlogReplyModelListテスト(引数エラー)
	 */
	@Test
	public void setBlogReplyModelListTest_ArgumentsError() {
		Method method = initSetBlogReplyModelList();

		try {
			int skipCnt = (int)method.invoke(this.dao, (List<Map<String, Object>>)null, 0, (BlogMainModel)null, true);
			assertNotNull(skipCnt);
			assertEquals(0, skipCnt);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * isBlogReplyModelテストの準備
	 */
	private Method initIsBlogReplyModel() {
		Method method = null;

		try {
			method = this.dao.getClass().getDeclaredMethod("isBlogReplyModel", Map.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return method;
	}

	/**
	 * isBlogReplyModelテスト
	 */
	@Test
	private void isBlogReplyModelTest() {
		Method method = initIsBlogReplyModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_AS_REPLY_ID, 1);

			boolean result = (boolean)method.invoke(this.dao, map);
			assertEquals(true, result);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * isBlogReplyModelテスト(空)
	 */
	@Test
	private void isBlogReplyModelTest_Empty() {
		Method method = initIsBlogReplyModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			boolean result = (boolean)method.invoke(this.dao, map);
			assertEquals(false, result);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * isBlogReplyModelテスト(空)
	 */
	@Test
	private void isBlogReplyModelTest_Null() {
		Method method = initIsBlogReplyModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_AS_REPLY_ID, null);
			boolean result = (boolean)method.invoke(this.dao, map);
			assertEquals(false, result);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * isBlogReplyModelテスト(引数エラー)
	 */
	@Test
	private void isBlogReplyModelTest_ArgumentsError() {
		Method method = initIsBlogReplyModel();

		try {
			boolean result = (boolean)method.invoke(this.dao, (Map<String, Object>)null);
			assertEquals(false, result);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * makeBlogModelテストの準備
	 */
	private Method initMakeBlogModel() {
		Method method = null;

		try {
			method = this.dao.getClass().getDeclaredMethod("makeBlogModel", Map.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return method;
	}

	/**
	 * makeBlogModelテスト
	 */
	@Test
	public void makeBlogModelTest() {
		Method method = initMakeBlogModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_ID,        1);
			map.put(PARAM_TITLE,     TestConsts.TEST_TITLE_NAME);
			map.put(PARAM_TAG,       TestConsts.TEST_TAG_NAME);
			map.put(PARAM_COMMENT,   TestConsts.TEST_COMMENT_NAME);
			map.put(PARAM_THANKSCNT, 1);
			map.put(PARAM_CREATED,   Timestamp.valueOf(TestConsts.TEST_TIME_01));
			map.put(PARAM_UPDATED,   Timestamp.valueOf(TestConsts.TEST_TIME_02));

			BlogMainModel resultModel = (BlogMainModel)method.invoke(this.dao, map);
			assertNotNull(resultModel);
			assertEquals(1, resultModel.getId());
			assertEquals(TestConsts.TEST_TITLE_NAME, 	resultModel.getTitle());
			assertEquals(TestConsts.TEST_TAG_NAME, 		resultModel.getTag());
			assertEquals(TestConsts.TEST_COMMENT_NAME, 	resultModel.getComment());
			assertEquals(1, resultModel.getThanksCnt());
			assertEquals(TestConsts.TEST_TIME_01.toString(), resultModel.getCreated().toString());
			assertEquals(TestConsts.TEST_TIME_02.toString(), resultModel.getUpdated().toString());
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeBlogModelテスト(空)
	 */
	@Test
	public void makeBlogModelTest_Empty() {
		Method method = initMakeBlogModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			BlogMainModel resultModel = (BlogMainModel)method.invoke(this.dao, map);
			assertNull(resultModel);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeBlogModelテスト(引数エラー)
	 */
	@Test
	public void makeBlogModelTest_ArgumentsError() {
		Method method = initMakeBlogModel();

		try {
			BlogMainModel resultModel = (BlogMainModel)method.invoke(this.dao, (Map<String, Object>)null);
			assertNull(resultModel);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * makeBlogReplyModelテストの準備
	 */
	private Method initMakeBlogReplyModel() {
		Method method = null;

		try {
			method = this.dao.getClass().getDeclaredMethod("makeBlogReplyModel", Map.class);
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		return method;
	}

	/**
	 * makeBlogReplyModelテスト
	 */
	@Test
	public void makeBlogReplyModelTest() {
		Method method = initMakeBlogReplyModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(PARAM_REPLY_ID,            1);
			map.put(PARAM_AS_REPLY_ID,         1);
			map.put(PARAM_AS_REPLY_NAME,       TEST_REPLY_NAME);
			map.put(PARAM_AS_REPLY_COMMENT,    TEST_REPLY_COMMENT);
			map.put(PARAM_AS_REPLY_THANKSCNT, 1);
			map.put(PARAM_AS_REPLY_CREATED,    Timestamp.valueOf(TestConsts.TEST_TIME_01));

			BlogReplyModel resultModel = (BlogReplyModel)method.invoke(this.dao, map);

			assertNotNull(resultModel);
			assertEquals(1, resultModel.getId());
			assertEquals(1, resultModel.getBlogId());
			assertEquals(TEST_REPLY_NAME, resultModel.getName());
			assertEquals(TEST_REPLY_COMMENT, resultModel.getComment());
			assertEquals(1, resultModel.getThanksCnt());
			assertEquals(TestConsts.TEST_TIME_01.toString(), resultModel.getCreated().toString());
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeBlogReplyModelテスト(空)
	 */
	@Test
	public void makeBlogReplyModelTest_Empty() {
		Method method = initMakeBlogReplyModel();

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			BlogReplyModel resultModel = (BlogReplyModel)method.invoke(this.dao, map);
			assertNull(resultModel);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * makeBlogReplyModelテスト(引数エラー)
	 */
	@Test
	public void makeBlogReplyModelTest_ArgumentsError() {
		Method method = initMakeBlogReplyModel();

		try {
			BlogReplyModel resultModel = (BlogReplyModel)method.invoke(this.dao, (Map<String, Object>)null);
			assertNull(resultModel);
		} catch (SecurityException 
				| IllegalAccessException 
				| IllegalArgumentException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------------------------------------

	/**
	 * 後処理
	 */
	@AfterEach
	public void Release() {
		this.dao = null;
		this.jdbcTemp = null;
	}
}
