package com.kk.mapper;

/*
@author kzj
@date 2020/3/26 - 12:06
*/

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kk.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
