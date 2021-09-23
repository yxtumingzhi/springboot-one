package com.hope.one.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-09-17 16:34
 */
@Data
public class FsCallInfoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 结果集
     */
    private List<Item> result;

    @Data
    public static class Item {
        /**
         * 自增主键
         */
        private Long id;

        /**
         * 来源平台
         */
        private Integer sourcePlatform;

        /**
         * 回调数据
         */
        private Object callBackData;

        /**
         * 创建时间
         */
        private Long createdTime;

        /**
         * 更新时间
         */
        private Long updatedTime;
    }
}
