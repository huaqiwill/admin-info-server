package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("u_port_check")
public class PortCheck {

    @TableId(type = IdType.AUTO)
    Integer id;

    Integer biologyNum;

    String portName;

    Integer abundance;

    BigDecimal abundanceRate;
}
