package com.example.demo.app.service.blog;

import java.util.List;

import com.example.demo.app.entity.blog.BlogMainModel;
import com.example.demo.app.exception.WebMvcConfig;
import com.example.demo.common.id.blog.BlogId;

/**
 * ブログメインサービスインターフェース
 * @author nanai
 *
 */
public interface BlogMainService {
	
	/**
	 * 保存
	 * @param model {@link BlogMainModel}
	 */
	void save(BlogMainModel model);
	
	/**
	 * 更新
	 * @param model {@link BlogMainModel}
	 */
	void update(BlogMainModel model);
	
	/**
	 * 削除
	 * @param id {@link BlogId}
	 * @throws {@link WebMvcConfig#SQL_NOT_UPDATE()}
	 */
	void delete(BlogId id);
	
	/**
	 * 全て選択
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	List<BlogMainModel> getAll();
	
	/**
	 * 全て選択(ブログ返信モデルリストつき)
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	List<BlogMainModel> getAllPlus(boolean isDesc);
	
	/**
	 * IDによる選択
	 * @param  id {@link BlogId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return ブログメインモデルクラス {@link BlogMainModel}
	 */
	BlogMainModel select(BlogId id);
	
	/**
	 * タグによる選択
	 * @param  タグ
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	List<BlogMainModel> select_byTag(String tag);
	
	/**
	 * タグによる選択(ブログ返信モデルリストつき)
	 * @param  タグ
	 * @param  isDesc false 昇順 true 降順
	 * @return ブログメインモデルリスト {@link List}({@link BlogMainModel})
	 */
	List<BlogMainModel> select_byTagPlus(String tag, boolean isDesc);
	
	/**
	 * いいね数加算
	 * @param id {@link BlogId}
	 * @throws {@link WebMvcConfig#NOT_FOUND()}
	 * @return いいね数
	 */
	int thanksIncrement(BlogId id);

}
