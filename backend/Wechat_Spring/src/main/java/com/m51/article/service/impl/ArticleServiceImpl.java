package com.m51.article.service.impl;

import com.m51.article.entity.Article;
import com.m51.article.mapper.ArticleMapper;
import com.m51.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.m51.user.entity.User;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean publish(Article articleBody , User user) {
        articleBody.setAuthorId(user.getId());
        articleBody.setAvatar(user.getAvatar());
        articleBody.setAuthorName(user.getNickname());
        articleBody.setDeleted(0);
        return save(articleBody);
    }
}
