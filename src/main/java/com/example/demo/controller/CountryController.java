package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.Country;
import com.example.demo.entity.User;
import com.example.demo.mapper.CountryMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Resource
    CountryMapper countryMapper;

    @PostMapping
    public Result<?> add(@RequestBody Country country) {
        countryMapper.insert(country);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Country country) {
        countryMapper.updateById(country);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> info(@PathVariable Long id) {
        Country country = countryMapper.selectById(id);
        return Result.success(country);
    }


    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        countryMapper.deleteById(id);
        return Result.success();
    }

    @DeleteMapping
    public Result<?> deleteBatch(@RequestParam List<Long> ids) {
        countryMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String name,
                          @RequestParam(defaultValue = "") String isTrader) {
        LambdaQueryWrapper<Country> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(name)) {
            wrappers.like(Country::getName, name);
        }

        if (StringUtils.isNotBlank(isTrader)) {
            wrappers.like(Country::getIsTrader, isTrader);
        }

        Page<Country> result = countryMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
