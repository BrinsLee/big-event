package com.brins.controller;

import com.brins.pojo.Category;
import com.brins.pojo.Result;
import com.brins.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lipeilin on 2024/1/24.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);
    }


    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        categoryService.delete(id);
        return Result.success();
    }

}
