package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 生物
 */
@TableName("u_biology")
@Data
public class Biology {

    @TableId(type = IdType.AUTO)
    Integer id;

    Integer num;

    String name;

    String belongTo;

    String harm;

    String worldDistribution;

    String latin;
}
