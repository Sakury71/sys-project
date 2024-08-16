package com.sakury.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: Sakury
 * Date: 2024/8/15 18:19
 * Version: 1.0
 * Description:
 */
@Data
@TableName("db_database")
@AllArgsConstructor
public class DataBase implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String year;
    private String location;
    private String type;
    private String details;
}
