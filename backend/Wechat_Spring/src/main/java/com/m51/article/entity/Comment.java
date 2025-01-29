package com.m51.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sean
 * @since 18:01:51
 */
@TableName("m_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long articleId;

    private Integer authorId;

    private String authorName;

    private String avatar;

    private String comment;

    private LocalDateTime fcd;

    private LocalDateTime lud;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getFcd() {
        return fcd;
    }

    public void setFcd(LocalDateTime fcd) {
        this.fcd = fcd;
    }

    public LocalDateTime getLud() {
        return lud;
    }

    public void setLud(LocalDateTime lud) {
        this.lud = lud;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id = " + id +
            ", articleId = " + articleId +
            ", authorId = " + authorId +
            ", authorName = " + authorName +
            ", avatar = " + avatar +
            ", comment = " + comment +
            ", fcd = " + fcd +
            ", lud = " + lud +
        "}";
    }
}
