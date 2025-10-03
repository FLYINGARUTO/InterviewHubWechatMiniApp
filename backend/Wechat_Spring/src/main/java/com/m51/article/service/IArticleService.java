package com.m51.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.m51.article.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.m51.article.vo.ArticleQuery;
import com.m51.user.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sean
 * @since 18:01:50
 */
public interface IArticleService extends IService<Article> {

    boolean publish(Article articleBody, User user);

    Page<Article> getArticleList(ArticleQuery query);

    Article getArticle(Long id);

//    Page<Article> getArticleList(ArticleQuery query);
}
