package com.sakury.entity.vo.response;

import lombok.Data;

import java.util.Date;

/**
 * @author Zhang
 * @package com.sakury.entity.vo.response
 * @date 2024/7/19 18:47
 */
//使用了Lombok的@Data注解，可以自动生成getter和setter方法，
//方便对属性进行访问和设置
//该类用于封装授权信息，可以在权限验证过程中传递和使用
@Data
public class AuthorizeVO {
    String username;
    String role;
    String token;
    Date expire;
}
