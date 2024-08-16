package com.sakury.service;

import com.sakury.entity.dto.DataBase;

import java.util.List;

/**
 * Author: Sakury
 * Date: 2024/8/15 18:21
 * Version: 1.0
 * Description:
 */
public interface DataBaseService {
    List<DataBase> selectAllByLocationOrderById(String location);
}
