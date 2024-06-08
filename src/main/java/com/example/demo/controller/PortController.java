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

@RestController
@RequestMapping("/port")
public class PortController {

    @Resource
    PortMapper portMapper;

    @PostMapping("/save")
    public Result<?> save(@RequestBody Port port) {
        if (port.getId() != null && portMapper.selectById(port.getId()) != null) {
            portMapper.updateById(port);
        } else {
            portMapper.insert(port);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        portMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          @RequestParam(defaultValue = "") String pName,
                          @RequestParam(defaultValue = "") String nName,
                          @RequestParam(defaultValue = "") String pShengFen) {
        LambdaQueryWrapper<Port> wrappers = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(pName)) {
            wrappers.like(Port::getCountry_name, pName);
        }

        if (StringUtils.isNotBlank(nName)) {
            wrappers.like(Port::getCountry_name, nName);
        }

        if (StringUtils.isNotBlank(pShengFen)) {
            wrappers.like(Port::getProvince, pShengFen);
        }

        Page<Port> result = portMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(result);
    }
}
