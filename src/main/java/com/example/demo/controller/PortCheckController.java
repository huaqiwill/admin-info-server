package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.PortCheck;
import com.example.demo.mapper.PortCheckMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/port-check")
public class PortCheckController {

    @Resource
    PortCheckMapper portCheckMapper;

    @PostMapping
    public Result<?> add(@RequestBody PortCheck portCheck) {
        portCheckMapper.insert(portCheck);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody PortCheck portCheck) {
        portCheckMapper.updateById(portCheck);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> info(@PathVariable Long id) {
        PortCheck portCheck = portCheckMapper.selectById(id);
        return Result.success(portCheck);
    }


    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        portCheckMapper.deleteById(id);
        return Result.success();
    }

    @DeleteMapping
    public Result<?> deleteBatch(@RequestParam List<Long> ids) {
        portCheckMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String biologyNum,
                          @RequestParam(defaultValue = "") String portName,
                          @RequestParam(defaultValue = "") String abundance) {
        LambdaQueryWrapper<PortCheck> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(biologyNum)) {
            wrappers.like(PortCheck::getPortName, biologyNum);
        }

        if (StringUtils.isNotBlank(portName)) {
            wrappers.like(PortCheck::getPortName, portName);
        }

        if (StringUtils.isNotBlank(abundance)) {
            wrappers.like(PortCheck::getAbundance, abundance);
        }

        Page<PortCheck> result = portCheckMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
