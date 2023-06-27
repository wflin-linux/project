package com.ccww.aiotlab.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author end
 * @date 2022年08月29日 21:18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompetitionVo {

    private Integer id;

    private Integer[] ids;

    private String name;

    private String title;

    private String content;
}
