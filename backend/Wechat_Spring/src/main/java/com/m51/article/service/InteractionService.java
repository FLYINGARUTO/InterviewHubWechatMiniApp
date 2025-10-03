package com.m51.article.service;

import io.swagger.v3.oas.models.security.SecurityScheme;

public interface InteractionService {

//    increment and get
     Long viewCountIncrement(Long articleId);
//      get
     Long getViewCount(Long articleId);

     Boolean follow(Integer authorId, Integer userId);

     Boolean unfollow(Integer authorId, Integer userId);

     Boolean isFollowing(Integer authorId, Integer userId);
     Long followerCount(Integer authorId);


}
