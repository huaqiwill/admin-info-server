package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.Country;
import com.example.demo.mapper.CountryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Resource
    CountryMapper countryMapper;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Country country) {
        if (country.getId() != null && countryMapper.selectById(country.getId()) != null) {
            countryMapper.updateById(country);
        } else {
            countryMapper.insert(country);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        countryMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String nName,
                          @RequestParam(defaultValue = "") String nMaoYi) {
        LambdaQueryWrapper<Country> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(nName)) {
            wrappers.like(Country::getName, nName);
        }

        if (StringUtils.isNotBlank(nMaoYi)) {
            wrappers.like(Country::getIsTrader, nMaoYi);
        }

        Page<Country> result = countryMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
