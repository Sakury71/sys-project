package com.sakury.controller;

import com.sakury.entity.RestBean;
import com.sakury.entity.dto.DataBase;
import com.sakury.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * className: DatabaseController
 * author: Sakury
 * date: 2024/8/12 10:30
 * Version: 1.0
 * description:
 */
@RestController
@RequestMapping("/data")
public class DatabaseController {
    @Autowired
    DataBaseService dataBaseService;
    @GetMapping("/test")
    public RestBean<List<DataBase>> selectAllByLocationOrderById(@RequestParam String location) {
        List<DataBase> dataBases = dataBaseService.selectAllByLocationOrderById(location);
        return RestBean.success(dataBases);
    }
    @GetMapping("/test2")
    public RestBean<List<DataBase>> selectAllByName(@RequestBody String name){
        List<DataBase> dataBases = dataBaseService.selectAllByName(name);
        return RestBean.success(dataBases);
    }
}
