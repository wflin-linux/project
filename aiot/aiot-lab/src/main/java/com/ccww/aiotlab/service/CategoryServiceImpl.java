package com.ccww.aiotlab.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.common.result.RespResultUtil;
import com.ccww.aiotlab.entity.Category;
import com.ccww.aiotlab.entity.vo.CategoryFontsVo;
import com.ccww.aiotlab.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author end
 * @date 2022年08月28日 20:42
 */
@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryMapper categoryMapper;

    public RespResult list() {
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<>());
        return RespResultUtil.success(categories);
    }

    public RespResult add(Category category) {
        categoryMapper.insert(category);
        return RespResultUtil.success("添加成功");
    }

    public RespResult delete(Integer id) {
        categoryMapper.deleteById(id);
        return RespResultUtil.success("删除成功");
    }

    public RespResult fontsList() {
        List<CategoryFontsVo> categoryFontsVos = categoryMapper.fontsList();
        return RespResultUtil.success(categoryFontsVos);
    }
}
