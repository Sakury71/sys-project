package com.sakury.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: Sakury
 * Date: 2024/8/15 20:07
 * Version: 1.0
 * Description:
 */

@RestController
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/test")
    public void test(){

    }
}
