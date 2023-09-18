package com.csx.service;

import com.csx.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csx.pojo.vo.PortalVo;
import com.csx.utils.Result;

/**
* @author fenbaitiao
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-09-13 22:21:22
*/
public interface HeadlineService extends IService<Headline> {

    Result getHeadLine(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(String token, Headline headline);

    Result updateDate(Headline headline);
}
