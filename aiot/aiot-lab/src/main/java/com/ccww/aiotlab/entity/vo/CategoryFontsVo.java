package com.ccww.aiotlab.entity.vo;

import com.ccww.aiotlab.entity.Competition;
import lombok.Data;

import java.util.List;

/**
 * @author end
 * @date 2022年09月09日 17:45
 */
@Data
public class CategoryFontsVo {

    private Integer id;

    private String name;

    private List<Competition> competitions;
}
