package com.sakury.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakury.entity.dto.DataBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: Sakury
 * Date: 2024/8/15 18:23
 * Version: 1.0
 * Description:
 */
@Mapper
public interface DataBaseMapper extends BaseMapper<DataBase> {

    List<DataBase> selectAllByLocationOrderById(@Param("location") String location);

}
