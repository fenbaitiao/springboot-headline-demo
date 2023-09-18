package com.csx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csx.pojo.Headline;
import com.csx.pojo.vo.PortalVo;
import com.csx.service.HeadlineService;
import com.csx.mapper.HeadlineMapper;
import com.csx.utils.JwtHelper;
import com.csx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author fenbaitiao
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-09-13 22:21:22
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{
    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result getHeadLine(PortalVo portalVo) {
        IPage<Map> mapIPage = new Page<>(portalVo.getPageNum(),portalVo.getPageSize());
        headlineMapper.selectMyPage(mapIPage,portalVo);
        List<Map> record = mapIPage.getRecords();
        Map map1 = new HashMap<>();
        map1.put("pageData",record);
        map1.put("pageNum",mapIPage.getCurrent());//当前页码数
        map1.put("pageSize",mapIPage.getSize());//页大小
        map1.put("totalPage",mapIPage.getPages());//总页码数
        map1.put("totalSize",mapIPage.getTotal());//总条数

        Map map2 = new HashMap<>();
        map2.put("pageInfo",map1);

        return Result.ok(map2);

    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map map = headlineMapper.queryDetailById(hid);
        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("headline",map);
        return Result.ok(map1);

    }

    @Override
    public Result publish(String token, Headline headline) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(null);


    }

    @Override
    public Result updateDate(Headline headline) {
        int version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}




