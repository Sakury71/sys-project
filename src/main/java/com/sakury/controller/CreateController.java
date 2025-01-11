package com.sakury.controller;

import com.sakury.entity.RestBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/create")
public class CreateController {
    @PostMapping ("/video")
    public RestBean<String> CreateVideoPost(@RequestBody Map<String, Object> requestBody){
        System.out.println(requestBody);
        System.out.println(requestBody.get("text"));
        String data = "服务器端已收到请求";
        return RestBean.success(data);
    }
}
