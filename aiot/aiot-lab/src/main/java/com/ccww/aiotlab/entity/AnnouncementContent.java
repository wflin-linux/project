package com.ccww.aiotlab.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName announcement_content
 */
@TableName(value ="announcement_content")
@Data
public class AnnouncementContent implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公告的具体内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 这个内容是哪个公告的
     */
    @TableField(value = "announce_id")
    private Integer announceId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}