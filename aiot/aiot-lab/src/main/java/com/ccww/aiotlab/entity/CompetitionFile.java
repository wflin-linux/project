package com.ccww.aiotlab.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author end
 * @date 2022年08月28日 13:57
 */
@Data
@TableName("competition_file")
public class CompetitionFile {

    @TableId
    private Integer id;

    /**
     * 文件的具体路径
     */
    @TableField("file_path")
    private String filePath;

    /**
     * 文件的真实名字
     */
    @TableField("real_name")
    private String realName;

    /**
     * 比赛公告的id
     */
    @TableField("competition_id")
    private Integer competitionId;
}
