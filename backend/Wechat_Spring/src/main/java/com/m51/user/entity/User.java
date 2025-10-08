package com.m51.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Sean
 * @since 18:02:33
 */
@Data
@TableName("m_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 1-普通注册用户，2-微信用户
     */
    private Integer type;

    /**
     * 微信用户则有该值
     */
    private String openid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String gender;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 到期日
     */
    private Date expireDate;

    /**
     * 用户等级
     */
    private String level;

    /**
     * 创建时间
     */
    private Date fcd;

    /**
     * 最后更新时间
     */
    private Date lud;

    /**
     * 头像地址
     */
    private String avatar;

    @TableField(exist = false)
    private String avatarUrl;

}
