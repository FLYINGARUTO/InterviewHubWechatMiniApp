package com.m51.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sean
 * @since 18:01:51
 */
@Data
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

    private Date fcd;

    private Date lud;

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
