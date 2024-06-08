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


@RestController
@RequestMapping("/port-check")
public class PortCheckController {

    @Resource
    PortCheckMapper portCheckMapper;

    @PostMapping("/save")
    public Result<?> save(@RequestBody PortCheck portCheck) {
        if (portCheck.getId() != null && portCheckMapper.selectById(portCheck.getId()) != null) {
            portCheckMapper.updateById(portCheck);
        } else {
            portCheckMapper.insert(portCheck);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        portCheckMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String pName) {
        LambdaQueryWrapper<PortCheck> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(pName)) {
            wrappers.like(PortCheck::getPortName, pName);
        }

        Page<PortCheck> result = portCheckMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
