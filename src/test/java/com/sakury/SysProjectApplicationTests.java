package com.sakury;

import com.sakury.entity.dto.DataBase;
import com.sakury.mapper.DataBaseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SysProjectApplicationTests {
    @Autowired
    DataBaseMapper dataBaseMapper;

    @Test
    void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

    @Test
    void test() {
        List<DataBase> dataBases = dataBaseMapper.selectAllByLocationOrderById("北京市");
        for (DataBase data : dataBases) {
            System.out.println(data);
        }
    }
    @Test
    void test2() {
        List<DataBase> dataBases = dataBaseMapper.selectAllByName("梁祝传说");
        for (DataBase data : dataBases) {
            System.out.println(data);
        }
    }
}
