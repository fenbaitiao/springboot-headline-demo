package com.csx.service;

import com.csx.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csx.utils.Result;

/**
* @author fenbaitiao
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-09-13 22:21:22
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);

    Result checkLogin(String token);
}
