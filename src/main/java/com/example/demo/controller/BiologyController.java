package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.Biology;
import com.example.demo.mapper.BiologyMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/biology")
public class BiologyController {

    @Resource
    BiologyMapper biologyMapper;

    @PostMapping
    public Result<?> add(@RequestBody Biology biology) {
        biologyMapper.insert(biology);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Biology biology) {
        biologyMapper.updateById(biology);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> info(@PathVariable Long id) {
        Biology biology = biologyMapper.selectById(id);
        return Result.success(biology);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        biologyMapper.deleteById(id);
        return Result.success();
    }

    @DeleteMapping
    public Result<?> deleteBatch(@RequestParam List<Long> ids) {
        biologyMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String name,
                          @RequestParam(defaultValue = "") String belongTo,
                          @RequestParam(defaultValue = "") String harm) {
        LambdaQueryWrapper<Biology> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(name)) {
            wrappers.like(Biology::getName, name);
        }

        if (StringUtils.isNotBlank(belongTo)) {
            wrappers.like(Biology::getBelongTo, belongTo);
        }

        if (StringUtils.isNotBlank(harm)) {
            wrappers.like(Biology::getHarm, harm);
        }

        Page<Biology> result = biologyMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
