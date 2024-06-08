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


@RestController
@RequestMapping("/port-find")
public class PortFindController {

    @Resource
    PortFindMapper portFindMapper;

    @PostMapping("/save")
    public Result<?> save(@RequestBody PortFind country) {
        if (country.getId() != null && portFindMapper.selectById(country.getId()) != null) {
            portFindMapper.updateById(country);
        } else {
            portFindMapper.insert(country);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        portFindMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String cName,
                          @RequestParam(defaultValue = "") String cMenShu,
                          @RequestParam(defaultValue = "") String pName) {
        LambdaQueryWrapper<PortFind> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(cName)) {
            wrappers.like(PortFind::getBiologyName, cName);
        }

        if (StringUtils.isNotBlank(cMenShu)) {
            wrappers.like(PortFind::getBiologyBelongTo, cMenShu);
        }

        if (StringUtils.isNotBlank(pName)) {
            wrappers.like(PortFind::getPortName, pName);
        }

        Page<PortFind> result = portFindMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
