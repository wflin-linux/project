package com.ccww.aiotlab.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author end
 * @date 2022年08月28日 20:39
 */
@Data
@TableName("category")
public class Category {

    private Integer id;

    /**
     * 比赛类别名
     */
    private String name;
}
