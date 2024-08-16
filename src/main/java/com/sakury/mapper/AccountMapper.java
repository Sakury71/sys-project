package com.sakury.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakury.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Zhang
 * @package com.sakury.mapper
 * @date 2024/7/20 8:21
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    Account findAccountByNameOrEmail(String text);
}
