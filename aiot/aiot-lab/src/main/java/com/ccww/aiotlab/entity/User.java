package com.ccww.aiotlab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@Accessors(chain = true)
public class User implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "email")
    @Email(message = "邮箱格式有误")
    private String email;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 数据更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 用户类型（0默认为普通用户，1为管理员，2为超管）
     */
    @TableField(value = "kind")
    private Integer kind;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 这个用户注册到底是谁同意的，记录同意的管理员 ID
     */
    @TableField(value = "who_agree")
    private Integer whoAgree;

    /**
     * 用户昵称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 真实名字
     */
    @TableField(value = "real_name")
    private String realName;

    @TableField(value = "salt")
    private String salt;

    @TableField(exist = false)
    private String code;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}