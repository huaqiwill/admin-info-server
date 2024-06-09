package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 港口
 */
@TableName("u_port")
@Data
public class Port {

    @TableId(type = IdType.AUTO)
    Integer id;

    Integer num;

    String name;

    String countryName;

    String province;
}
