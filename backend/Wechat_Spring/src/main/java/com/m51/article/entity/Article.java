package com.m51.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sean
 * @since 18:01:50
 */
@Data
@TableName("m_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 详情
     */
    private String details;

    /**
     * 分类
     */
    private Integer categoryId;

    /**
     * 作者id
     */
    private Integer authorId;

    /**
     * 作者名
     */
    private String authorName;

    private String avatar;

    /**
     * 逻辑删除状态
     */
    private Integer deleted;

    /**
     * 创建日期
     */
    private LocalDateTime fcd;

    /**
     * 最后修改日期
     */
    private LocalDateTime lud;

    @TableField(exist = false)
    private Map<String,Object> other;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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
        return "Article{" +
            "id = " + id +
            ", title = " + title +
            ", details = " + details +
            ", categoryId = " + categoryId +
            ", authorId = " + authorId +
            ", authorName = " + authorName +
            ", avatar = " + avatar +
            ", deleted = " + deleted +
            ", fcd = " + fcd +
            ", lud = " + lud +
        "}";
    }
}
