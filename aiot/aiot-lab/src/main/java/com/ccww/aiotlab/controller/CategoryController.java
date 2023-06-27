package com.ccww.aiotlab.controller;

import com.ccww.aiotlab.common.result.RespResult;
import com.ccww.aiotlab.entity.Category;
import com.ccww.aiotlab.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author end
 * @date 2022年08月28日 20:39
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("list")
    public RespResult list() {
        return categoryService.list();
    }

    @PostMapping("add")
    public RespResult add(@RequestBody Category category) {
        return categoryService.add(category);
    }

    @PostMapping("delete")
    public RespResult delete(@RequestParam("id") Integer id) {
        return categoryService.delete(id);
    }

    @GetMapping("fonts/list")
    public RespResult fontsList() {
        return categoryService.fontsList();
    }
}
