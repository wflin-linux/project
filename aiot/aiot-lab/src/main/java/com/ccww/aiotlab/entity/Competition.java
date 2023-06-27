package com.ccww.aiotlab.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author end
 * @date 2022年08月28日 13:42
 */
@Data
@TableName("competition")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Competition {

    @TableId
    private Integer id;

    /**
     * 比赛公告标题
     */
    private String title;

    /**
     * 比赛公告内容
     */
    private String content;

    /**
     * 比赛公告类别名
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 比赛公告的发布时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 比赛公告的更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 发布比赛公告的用户 id
     */
    private Integer creator;

}
