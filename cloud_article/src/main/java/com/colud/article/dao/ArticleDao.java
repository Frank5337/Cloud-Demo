package com.colud.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.colud.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    /**
     * 增删改都需要加modifying 审核
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET state = '1' WHERE id = ?", nativeQuery = true)
    public void updateState(String id);

    /**
     *  点赞
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup = thumbup + 1  WHERE id = ?", nativeQuery = true)
    public void addThumbup(String id);
}
