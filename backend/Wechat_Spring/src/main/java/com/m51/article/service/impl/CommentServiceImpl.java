package com.m51.article.service.impl;

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

}
