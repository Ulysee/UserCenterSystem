package com.example.usercenter.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * identification
     */
    @TableId(value = "id")
    private Long id;

    /**
     * userAccount
     */
    @TableField(value = "userAccount")
    private String userAccount;

    /**
     * username
     */
    @TableField(value = "userName")
    private String userName;

    /**
     * avatarUrl
     */
    @TableField(value = "avatarUrl")
    private String avatarUrl;

    /**
     * gender
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * userPassword
     */
    @TableField(value = "userPassword")
    private String userPassword;

    /**
     * phoneNum
     */
    @TableField(value = "phoneNum")
    private String phoneNum;

    /**
     * email
     */
    @TableField(value = "email")
    private String email;

    /**
     * userStatus
     */
    @TableField(value = "userStatus")
    private Integer userStatus;

    /**
     * createTime
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * updateTime
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * ifDelete
     */
    @TableField(value = "ifDelete")
    @TableLogic
    private Integer ifDelete;

    @TableField(value = "role")
    private Integer role;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}