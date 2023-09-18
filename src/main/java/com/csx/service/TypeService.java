package com.csx.service;

import com.csx.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csx.utils.Result;

/**
* @author fenbaitiao
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-09-13 22:21:22
*/
public interface TypeService extends IService<Type> {

    Result findAllType();
}
