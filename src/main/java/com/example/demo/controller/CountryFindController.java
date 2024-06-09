package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.CountryFind;
import com.example.demo.mapper.CountryFindMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/country-find")
public class CountryFindController {

    @Resource
    CountryFindMapper countryFindMapper;

    @PostMapping
    public Result<?> add(@RequestBody CountryFind country) {
        countryFindMapper.insert(country);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody CountryFind country) {
        countryFindMapper.updateById(country);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        countryFindMapper.deleteById(id);
        return Result.success();
    }

    @DeleteMapping
    public Result<?> deleteBatch(@RequestParam List<Long> ids) {
        countryFindMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String biologyNum,
                          @RequestParam(defaultValue = "") String countryName,
                          @RequestParam(defaultValue = "") String foundLocation) {
        LambdaQueryWrapper<CountryFind> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(biologyNum)) {
            wrappers.like(CountryFind::getBiologyNum, biologyNum);
        }

        if (StringUtils.isNotBlank(countryName)) {
            wrappers.like(CountryFind::getCountryName, countryName);
        }

        if (StringUtils.isNotBlank(foundLocation)) {
            wrappers.like(CountryFind::getFoundLocation, foundLocation);
        }

        Page<CountryFind> result = countryFindMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
