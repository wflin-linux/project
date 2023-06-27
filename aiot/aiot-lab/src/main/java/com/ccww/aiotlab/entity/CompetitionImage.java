package com.ccww.aiotlab.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author end
 * @date 2022年08月28日 13:54
 */
@Data
@TableName("competition_image")
public class CompetitionImage {

    @TableId
    private Integer id;

    /**
     * 图片的具体路径
     */
    @TableField("img_path")
    private String imgPath;

    /**
     * 比赛公告的id
     */
    @TableField("competition_id")
    private Integer competitionId;

}
