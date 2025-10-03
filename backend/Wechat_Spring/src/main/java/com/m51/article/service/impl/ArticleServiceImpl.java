package com.m51.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.m51.article.entity.Article;
import com.m51.article.mapper.ArticleMapper;
import com.m51.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.m51.article.vo.ArticleQuery;
import com.m51.user.entity.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sean
 * @since 18:01:50
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

//    @Override
//    public Page<Article> getArticleList(ArticleQuery query) {
//        Page<Article> page=new Page<>(query.getPageNo(), query.getPageSize());
//        LambdaQueryWrapper<Article> wrapper=new LambdaQueryWrapper<>();
//        wrapper.like(StringUtils.hasLength(query.getTitle()),Article::getTitle,query.getTitle());
//        wrapper.eq(Article::getCategoryId,query.getCategoryId());
//        this.page(page,wrapper);
//        return page;
//

    @Override
    public Article getArticle(Long id) {
        LambdaQueryWrapper<Article> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Article::getId,id);
        return this.getOne(wrapper);

    }

    @Override
    public Page<Article> getArticleList(ArticleQuery query) {
        Page<Article> page=new Page<>(query.getPageNo(), query.getPageSize());
        LambdaQueryWrapper<Article> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(query.getTitle()),Article::getTitle,query.getTitle());
        wrapper.eq(query.getCategoryId()!=null,Article::getCategoryId,query.getCategoryId());
        this.page(page,wrapper);
        return page;
    }
//    }

    @Override
    public boolean publish(Article articleBody , User user) {
        articleBody.setAuthorId(user.getId());
        articleBody.setAvatar(user.getAvatar());
        articleBody.setAuthorName(user.getNickname());
        articleBody.setDeleted(0);
        return save(articleBody);
    }
}
