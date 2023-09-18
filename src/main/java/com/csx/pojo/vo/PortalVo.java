package com.csx.pojo.vo;

import lombok.Data;

/**
 * @author 曹某
 * @version 1.0
 * description:
 */
@Data
public class PortalVo {
    private String keyWords;
    private Integer type;
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
