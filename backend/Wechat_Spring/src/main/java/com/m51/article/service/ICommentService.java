package com.m51.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.m51.article.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Sean
 * @since 18:01:51
 */
public interface ICommentService extends IService<Comment> {
    Boolean comment(Comment comment);

    Page<Comment> commentList(Long articleId,int pageNo,int pageSize);

}
