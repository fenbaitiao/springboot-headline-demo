package com.csx.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csx.pojo.User;
import com.csx.service.UserService;
import com.csx.mapper.UserMapper;
import com.csx.utils.JwtHelper;
import com.csx.utils.MD5Util;
import com.csx.utils.Result;
import com.csx.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author fenbaitiao
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-09-13 22:21:22
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginuser = userMapper.selectOne(lambdaQueryWrapper);

        if(loginuser == null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }

        if (!StringUtils.isEmpty(user.getUserPwd())
              && MD5Util.encrypt(user.getUserPwd()).equals(loginuser.getUserPwd()) ){
            //登录成功
            String token = jwtHelper.createToken(Long.valueOf(loginuser.getUid()));
            Map map = new HashMap();
            map.put("token",token);
            return  Result.ok(map);
        }

        //登录失败
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        int id = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(id);
        user.setUserPwd(null);

        Map map = new HashMap();
        map.put("loginUser",user);
        return Result.ok(map);


    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if(count == 0){
            return  Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }


    //检查用户名是否被使用
    //被使用就返回错误信息
    //把密码用md5加密放到数据库中
    //返回成功信息
    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if(count > 0){
            return  Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        int insert = userMapper.insert(user);
        if (insert > 0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public Result checkLogin(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        return expiration ? Result.build(null,ResultCodeEnum.NOTLOGIN) : Result.ok(null);
    }
}




