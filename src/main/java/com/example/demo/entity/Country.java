package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 国家
 */
@TableName("u_country")
@Data
public class Country {
    @TableId(type = IdType.AUTO)
    Integer id;

    String num;

    String name;

    String isTrader;
}
