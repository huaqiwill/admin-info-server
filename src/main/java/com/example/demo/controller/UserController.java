package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.utils.LoginUser;
import com.example.demo.commom.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.TokenUtils;


import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.error("-1", "用户名已重复");
        }
        userMapper.insert(user);
        return Result.success();
    }

    @CrossOrigin
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (res == null) {
            return Result.error("-1", "用户名或密码错误");
        }
        String token = TokenUtils.genToken(res);
        res.setToken(token);
        LoginUser.addVisitCount();
        return Result.success(res);
    }

    @PostMapping
    public Result<?> add(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        user.setRole(2);
        userMapper.insert(user);
        return Result.success();
    }

    @PutMapping("/password")
    public Result<?> password(@RequestParam Integer id, @RequestParam String password2) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        User user = new User();
        user.setPassword(password2);
        userMapper.update(user, updateWrapper);
        return Result.success();
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    @DeleteMapping
    public Result<?> deleteBatch(@RequestParam List<Integer> ids) {
        userMapper.deleteBatchIds(ids);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> info(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < user.getPassword().length(); i++) {
            password.append("*");
        }
        user.setPassword(password.toString());
        return Result.success(user);
    }

    @GetMapping
    public Result<?> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String id,
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "") String phone,
            @RequestParam(defaultValue = "") String address) {
        LambdaQueryWrapper<User> wrappers = Wrappers.<User>lambdaQuery();
        if (StringUtils.isNotBlank(id)) {
            wrappers.like(User::getId, id);
        }
        if (StringUtils.isNotBlank(username)) {
            wrappers.like(User::getUsername, username);
        }
        if (StringUtils.isNotBlank(phone)) {
            wrappers.like(User::getPhone, phone);
        }
        if (StringUtils.isNotBlank(address)) {
            wrappers.like(User::getAddress, address);
        }
        wrappers.like(User::getRole, 2);
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrappers);
        return Result.success(userPage);
    }
}
