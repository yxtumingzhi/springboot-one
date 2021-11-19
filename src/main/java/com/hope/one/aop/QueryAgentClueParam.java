package com.hope.one.aop;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-18 17:12
 */
@Getter
@Setter
public class QueryAgentClueParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer groupType;

    @NotNull
    private Integer groupId;

    @NotNull
    private Long startCreatedTime;

    @NotNull
    private Long endCreatedTime;

    @NotNull
    private PageInfo page;

}
