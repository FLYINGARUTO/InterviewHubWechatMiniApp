package com.m51.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.m51.article.entity.Comment;
import com.m51.article.mapper.CommentMapper;
import com.m51.article.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sean
 * @since 18:01:51
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Override
    public Boolean comment(Comment comment) {
        return this.save(comment);
    }

    @Override
    public Page<Comment> commentList(Long articleId, int pageNo, int pageSize) {
        Page<Comment> page=new Page<>(pageNo,pageSize);
        LambdaQueryWrapper<Comment> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getArticleId,articleId);
        wrapper.orderByDesc(Comment::getFcd);
        return this.page(page,wrapper);
    }
}
