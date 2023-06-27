package com.ccww.aiotlab.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author end
 * @date 2022年08月28日 15:16
 */
@Data
public class CompetitionBackVo {

    private Integer id;

    private String title;

    private String categoryName;

    private Date createTime;

    private String creator;
}
