package com.csx.controller;

import com.csx.pojo.vo.PortalVo;
import com.csx.service.HeadlineService;
import com.csx.service.TypeService;
import com.csx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 曹某
 * @version 1.0
 * description:
 */
@RestController
@RequestMapping("portal")
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllType(){
        Result result = typeService.findAllType();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result getHeadLine(@RequestBody PortalVo portalVo){
        Result result = headlineService.getHeadLine(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }



}
