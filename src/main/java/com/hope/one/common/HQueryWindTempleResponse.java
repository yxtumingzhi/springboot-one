package com.hope.one.common;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HQueryWindTempleResponse implements Serializable {

    @ApiModelProperty(value = "成交单辆奖池增加积分数")
    private Integer makeDealPoolIncrease;

    @ApiModelProperty(value = "线索留资奖励合伙人积分数")
    private Integer clueRetentionRewardPartner;

    @ApiModelProperty(value = "线索试驾奖励合伙人积分数")
    private Integer clueDriveRewardPartner;

    @ApiModelProperty(value = "被推荐人成交单辆车奖励金额/单位元")
    private Integer refereeMakeDealRewardPartner;

    @ApiModelProperty(value = "被推荐人成交单辆车奖励积分数")
    private Integer refereeMakeDealRewardReferee;

    @ApiModelProperty(value = "合伙人称号及分红设置")
    private List<TitleAndDividendConfig> partnerTitleSet;

    @ApiModelProperty(value = "首次进入合伙人弹框")
    private String firstInPartnerMsg;

    @ApiModelProperty(value = "首次进入收款弹框")
    private String firstInReceiveAccountMsg;

    @Data
    public static class TitleAndDividendConfig {

        @ApiModelProperty(value = "等级名称")
        private String title;

        @ApiModelProperty(value = "成交车辆数-起始数")
        private Integer carNumStart;

        @ApiModelProperty(value = "成交车辆数-结束数")
        private Integer carNumEnd;

        @ApiModelProperty(value = "每台奖励积分数")
        private Integer eachReward;
    }

}
