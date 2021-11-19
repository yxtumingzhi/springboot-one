package com.hope.one.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-09-17 16:53
 */
@Data
public class FetchFsCallInfoRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 来源平台
     */
    private Integer sourcePlatform;

    /**
     * 起始主键
     */
    private Long startId;

    /**
     * 查询条数
     */
    private Integer limit;

}
