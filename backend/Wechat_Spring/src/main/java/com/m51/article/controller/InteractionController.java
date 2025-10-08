package com.m51.article.controller;

import com.m51.article.dto.LikeRequest;
import com.m51.article.service.InteractionService;
import com.m51.common.util.JwtUtil;
import com.m51.common.vo.Result;
import com.m51.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.OnClose;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name="互动操作")
@RestController
@RequestMapping("/interaction")
public class InteractionController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisTemplate template;
    @Autowired
    private InteractionService interactionService;

    @Operation(summary = "follow")
    @GetMapping("/follow/{authorId}")
    public Result<Object> follow(@PathVariable("authorId") Integer authorId, @RequestHeader("Authorization") String token){
        User user=jwtUtil.parseJwt(token, User.class);

        if(interactionService.follow(authorId, user.getId())){
            return Result.Success();
        }
        return Result.Fail();
    }

    @Operation(summary = "unfollow")
    @GetMapping("/unfollow/{authorId}")
    public Result<Object> unfollow(@PathVariable("authorId") Integer authorId,@RequestHeader("Authorization") String token){
        User user=jwtUtil.parseJwt(token, User.class);

        if(interactionService.unfollow(authorId, user.getId())){
            return Result.Success();
        }
        return Result.Fail();
    }
    @Operation(summary = "点赞")
    @PostMapping("/like")
    public Result<Object> like(@RequestBody LikeRequest likeRequest){
        Map<String, Object> interaction = interactionService.like(likeRequest.getArticleId(), likeRequest.getAuthorId(), likeRequest.getUserId());
        return Result.Success(interaction);
    }

    @Operation(summary = "点赞")
    @DeleteMapping("/unlike")
    public Result<Object> unlike(@RequestBody LikeRequest likeRequest){
        Map<String, Object> interaction=interactionService.unlike(likeRequest.getArticleId(), likeRequest.getAuthorId(), likeRequest.getUserId());
        return Result.Success(interaction);
    }

    @Operation(summary = "收藏")
    @PostMapping("/star")
    public Result<Object> star(@RequestBody LikeRequest likeRequest){
        Map<String, Object> interaction = interactionService.star(likeRequest.getArticleId(), likeRequest.getAuthorId(), likeRequest.getUserId());
        return Result.Success(interaction);
    }

    @Operation(summary = "取消收藏")
    @DeleteMapping("/unstar")
    public Result<Object> unstar(@RequestBody LikeRequest likeRequest){
        Map<String, Object> interaction=interactionService.unstar(likeRequest.getArticleId(), likeRequest.getAuthorId(), likeRequest.getUserId());
        return Result.Success(interaction);
    }
    @Operation(summary = "获取某篇文章的互动数据")
    @GetMapping("/article/{articleId}/{userId}")
    public Result<Map<String,Object>> getInteraction(@PathVariable("articleId") Long articleId,

                                                     @PathVariable("userId") Integer userId){
        Map<String, Object> interaction = interactionService.getInteraction(articleId, userId);
        return Result.Success(interaction);
    }

    @Operation(summary = "获取某个用户的获赞量、关注量、被关注量和收藏夹数量")
    @GetMapping("/user-statistics/{userId}")
    public Result<Map<String,Object>> getUserStatistics(@PathVariable Integer userId){
        Map<String,Object> statistics = interactionService.getUserStatistics(userId);
        return Result.Success(statistics);
    }

}
