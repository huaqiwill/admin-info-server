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

@RestController
@RequestMapping("/biology")
public class BiologyController {

    @Resource
    BiologyMapper biologyMapper;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Biology biology) {
        if (biology.getId() != null && biologyMapper.selectById(biology.getId()) != null) {
            biologyMapper.updateById(biology);
        } else {
            biologyMapper.insert(biology);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        biologyMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String cName,
                          @RequestParam(defaultValue = "") String cMenShu) {
        LambdaQueryWrapper<Biology> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(cName)) {
            wrappers.like(Biology::getName, cName);
        }

        if (StringUtils.isNotBlank(cMenShu)) {
            wrappers.like(Biology::getBelongTo, cMenShu);
        }

        Page<Biology> result = biologyMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
