package com.csx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csx.pojo.Type;
import com.csx.service.TypeService;
import com.csx.mapper.TypeMapper;
import com.csx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author fenbaitiao
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-09-13 22:21:22
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result findAllType() {
        List<Type> typeList = typeMapper.selectList(null);
        return Result.ok(typeList);
    }
}




