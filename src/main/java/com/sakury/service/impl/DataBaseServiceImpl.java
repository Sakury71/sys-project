package com.sakury.service.impl;

import com.sakury.entity.dto.DataBase;
import com.sakury.mapper.DataBaseMapper;
import com.sakury.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Sakury
 * Date: 2024/8/15 18:22
 * Version: 1.0
 * Description:
 */
@Service
public class DataBaseServiceImpl implements DataBaseService {
    @Autowired
    DataBaseMapper dataBaseMapper;
    @Override
    public List<DataBase> selectAllByLocationOrderById(String location) {
        return dataBaseMapper.selectAllByLocationOrderById(location);
    }

    @Override
    public List<DataBase> selectAllByName(String name) {
        return dataBaseMapper.selectAllByName(name);
    }
}
