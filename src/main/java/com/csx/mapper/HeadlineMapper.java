package com.csx.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.csx.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csx.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;


import java.util.Map;

/**
* @author fenbaitiao
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-09-13 22:21:22
* @Entity com.csx.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map queryDetailById(int id);
}




