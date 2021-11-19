package com.hope.one.aop;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class HapiQueryAgentClueParam implements Serializable {

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
