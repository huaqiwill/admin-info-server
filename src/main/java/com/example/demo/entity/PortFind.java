package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("u_port_find")
public class PortFind {

    @TableId(type = IdType.AUTO)
    Integer id;

    Integer biologyNum;

    String biologyName;

    String biologyBelongTo;

    String portName;

    Date firstFoundTime;

    String firstFoundLocation;
}
