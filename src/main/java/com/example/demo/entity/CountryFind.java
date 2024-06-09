package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("u_country_find")
@Data
public class CountryFind {
    @TableId(type = IdType.AUTO)
    Integer id;

    Integer biologyNum;

    Integer countryName;

    Date foundTime;

    String foundLocation;

    BigDecimal longitude;

    BigDecimal latitude;
}
