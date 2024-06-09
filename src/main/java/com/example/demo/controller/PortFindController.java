package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.PortFind;
import com.example.demo.mapper.PortFindMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/port-find")
public class PortFindController {

    @Resource
    PortFindMapper portFindMapper;

    @PostMapping
    public Result<?> add(@RequestBody PortFind country) {
        portFindMapper.insert(country);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody PortFind country) {
        portFindMapper.updateById(country);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> info(@PathVariable Long id) {
        PortFind portFind = portFindMapper.selectById(id);
        return Result.success(portFind);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        portFindMapper.deleteById(id);
        return Result.success();
    }

    @DeleteMapping
    public Result<?> deleteBatch(@PathVariable List<Long> ids) {
        portFindMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String biologyName,
                          @RequestParam(defaultValue = "") String portName,
                          @RequestParam(defaultValue = "") String firstFoundLocation) {
        LambdaQueryWrapper<PortFind> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(biologyName)) {
            wrappers.like(PortFind::getBiologyName, biologyName);
        }

        if (StringUtils.isNotBlank(portName)) {
            wrappers.like(PortFind::getPortName, portName);
        }

        if (StringUtils.isNotBlank(firstFoundLocation)) {
            wrappers.like(PortFind::getFirstFoundLocation, firstFoundLocation);
        }

        Page<PortFind> result = portFindMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
