package com.sakury.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sakury.entity.dto.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Zhang
 * @package com.sakury.service
 * @date 2024/7/20 8:23
 */
public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
}
