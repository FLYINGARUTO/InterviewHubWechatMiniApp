package com.m51.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.m51.article.dto.CommentRequest;
import com.m51.article.entity.Comment;
import com.m51.article.service.ICommentService;
import com.m51.common.vo.Result;
import com.m51.file.utils.MinioUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Sean
 * @since 18:01:51
 */
@RestController
@Tag(name ="评论")
@RequestMapping("/article/comment")
public class CommentController {
    @Autowired
    MinioUtils minioUtils;

    @Autowired
    private ICommentService commentService;

    @Operation(summary = "新建评论")
    @PostMapping("/new")
    public Result<Object> comment(@RequestBody Comment comment){
        Boolean comment1 = commentService.comment(comment);
        return comment1.equals(Boolean.TRUE) ? Result.Success():Result.Fail("评论失败");
    }

    @Operation(summary = "返回评论列表")
    @PostMapping("/list")
    public Result<List<Comment>> commentList(@RequestBody CommentRequest request){
        Page<Comment> commentPage=commentService.commentList(request.getArticleId(), request.getPageNo(),request.getPageSize());
        List<Comment> commentList = commentPage.getRecords().stream().map(item -> {
            item.setAvatar(minioUtils.getUrl(item.getAvatar()));//拼接实际头像url

            return item;
        }).toList();
        return Result.Success(commentList);
    }
}
