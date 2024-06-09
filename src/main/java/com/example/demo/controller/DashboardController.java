package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.utils.LoginUser;
import com.example.demo.commom.Result;

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

    @Resource
    private PortMapper portMapper;

    @Resource
    private PortFindMapper portFindMapper;

    @Resource
    private PortCheckMapper portCheckMapper;

    @Resource
    private CountryFindMapper countryFindMapper;

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
        // 港口数
        QueryWrapper<Port> queryWrapper4 = new QueryWrapper();
        int portCount = portMapper.selectCount(queryWrapper4);
        // 港口发现生物
        QueryWrapper<PortFind> queryWrapper5 = new QueryWrapper();
        int portFindCount = portFindMapper.selectCount(queryWrapper5);
        // 港口检测生物
        QueryWrapper<PortCheck> queryWrapper6 = new QueryWrapper();
        int portCheckCount = portCheckMapper.selectCount(queryWrapper6);
        // 国家发现有害生物
        QueryWrapper<CountryFind> queryWrapper7 = new QueryWrapper();
        int countryFindCount = countryFindMapper.selectCount(queryWrapper7);

        Map<String, Object> map = new HashMap<>();//键值对形式
        map.put("visitCount", visitCount);//放置visitCount到map中
        map.put("userCount", userCount);
        map.put("biologyCount", biologyCount);
        map.put("countryCount", countryCount);
        map.put("portCount", portCount);
        map.put("portFindCount", portFindCount);
        map.put("portCheckCount", portCheckCount);
        map.put("countryFindCount", countryFindCount);

        return Result.success(map);
    }
}
