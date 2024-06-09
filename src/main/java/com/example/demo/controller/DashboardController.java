package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.utils.LoginUser;
import com.example.demo.commom.Result;
import com.example.demo.entity.Biology;
import com.example.demo.entity.Country;
import com.example.demo.entity.User;
import com.example.demo.mapper.BiologyMapper;
import com.example.demo.mapper.CountryMapper;
import com.example.demo.mapper.UserMapper;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BiologyMapper lendRecordMapper;

    @Resource
    private CountryMapper bookMapper;

    @GetMapping
    public Result<?> records() {
        // 总访问
        int visitCount = LoginUser.getVisitCount();
        // 用户数
        QueryWrapper<User> queryWrapper1 = new QueryWrapper();
        int userCount = userMapper.selectCount(queryWrapper1);
        // 生物数
        QueryWrapper<Biology> queryWrapper2 = new QueryWrapper();
        int biologyCount = lendRecordMapper.selectCount(queryWrapper2);
        // 国家数
        QueryWrapper<Country> queryWrapper3 = new QueryWrapper();
        int countryCount = bookMapper.selectCount(queryWrapper3);

        Map<String, Object> map = new HashMap<>();//键值对形式
        map.put("visitCount", visitCount);//放置visitCount到map中
        map.put("userCount", userCount);
        map.put("biologyCount", biologyCount);
        map.put("countryCount", countryCount);

        return Result.success(map);
    }
}
