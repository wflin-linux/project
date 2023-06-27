package com.ccww.aiotlab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccww.aiotlab.entity.Category;
import com.ccww.aiotlab.entity.vo.CategoryFontsVo;

import java.util.List;

/**
 * @author end
 * @date 2022年08月28日 20:41
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryFontsVo> fontsList();
}
