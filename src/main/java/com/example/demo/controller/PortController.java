package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.Port;
import com.example.demo.mapper.PortMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/port")
public class PortController {

    @Resource
    PortMapper portMapper;

    @PostMapping
    public Result<?> add(@RequestBody Port port) {
        portMapper.insert(port);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody Port port) {
        portMapper.updateById(port);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> add(@PathVariable Long id) {
        Port port = portMapper.selectById(id);
        return Result.success(port);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        portMapper.deleteById(id);
        return Result.success();
    }

    @DeleteMapping
    public Result<?> deleteBatch(@RequestParam("ids") List<Long> ids) {
        portMapper.deleteBatchIds(ids);
        return Result.success();
    }


    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String countryName, @RequestParam(defaultValue = "") String portName, @RequestParam(defaultValue = "") String province) {
        LambdaQueryWrapper<Port> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(countryName)) {
            wrappers.like(Port::getCountryName, countryName);
        }

        if (StringUtils.isNotBlank(portName)) {
            wrappers.like(Port::getName, portName);
        }

        if (StringUtils.isNotBlank(province)) {
            wrappers.like(Port::getProvince, province);
        }

        Page<Port> result = portMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
