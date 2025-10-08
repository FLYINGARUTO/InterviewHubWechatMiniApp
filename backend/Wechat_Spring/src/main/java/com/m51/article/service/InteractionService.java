package com.m51.article.service;

import io.swagger.v3.oas.models.security.SecurityScheme;

import java.util.Map;

public interface InteractionService {

//    increment and get
     Long viewCountIncrement(Long articleId);
//      get
     Long getViewCount(Long articleId);

     Boolean follow(Integer authorId, Integer userId);

     Boolean unfollow(Integer authorId, Integer userId);

     Boolean isFollowing(Integer authorId, Integer userId);
     Long followerCount(Integer authorId);
     Long followingCount(Integer userId);

     /**
      * 点赞
      */
     Map<String,Object> like(Long articleId,Integer authorId,Integer userId);
     /**
      * 取消点赞
      */
     Map<String,Object> unlike(Long articleId,Integer authorId,Integer userId);
     /**
      * 是否点赞
      */
     Boolean isLike(Long articleId,Integer userId);
     /**
      * 文章获赞数
      */
     Long getArticleLikeCount(Long articleId);
     /**
      * 获取作者总共获赞数
      */
     Long getAuthorLikeCount(Integer authorId);
     /**
      * 收藏
      */
     Map<String,Object> star(Long articleId,Integer authorId,Integer userId);
     /**
      * 取消收藏
      */
     Map<String,Object> unstar(Long articleId,Integer authorId,Integer userId);
     /**
      * 是否收藏
      */
     Boolean isStar(Long articleId,Integer userId);
     /**
      * 文章获收藏数
      */
     Long getArticleStarCount(Long articleId);
     /**
      * 获取作者总共获收藏数
      */
     Long getAuthorStarCount(Integer authorId);
     /**
      * 获取互动数据
      */
     Map<String,Object> getInteraction(Long articleId,Integer userId);


     Map<String, Object> getUserStatistics(Integer userId);
}
