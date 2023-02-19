package com.example.demo.app.dao.blog;

import java.util.List;

import com.example.demo.app.common.id.blog.BlogTagId;
import com.example.demo.app.entity.blog.BlogTagModel;

/**
 * ブログタグDaoインターフェース
 * @author nanai
 *
 */
public interface BlogTagDao {
	
	/**
	 * 追加
	 * @param model
	 * @return 0以下 失敗 それ以外 成功
	 */
	void insertTag(BlogTagModel model);
	
	/**
	 * 更新
	 * @param model
	 * @return 0以下 失敗 それ以外 成功
	 */
	int updateTag(BlogTagModel model);
	
	/**
	 * 削除
	 * @param id
	 * @return 0以下 失敗 それ以外 成功 
	 */
	int deleteTag(BlogTagId id);
	
	/**
	 * 全て選択
	 * @return ブログタグモデルリスト
	 */
	List<BlogTagModel> getAll();
	
	/**
	 * タグが存在するかチェック
	 * @param target
	 * @return true タグ存在する false タグ存在しない
	 */
	boolean isSelect_byTag(String target);
	
	/**
	 * 選択
	 * @param id
	 * @return ブログタグモデル
	 */
	BlogTagModel select(BlogTagId id);

}
