package com.hope.one.work;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Data
public class HapiBaseGetShareImgReq implements Serializable {

	/**
     * 必须是已经发布的小程序存在的页面（否则报错），
     * 例如 "pages/index/index" ,根路径前不要填加'/',不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
     */
	@NotBlank(message = "page不能为空")
	@ApiModelProperty(value = "分享页（参见微信文档 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html）", example = "pages/land/index/index", required = true)
    private String page = UUID.randomUUID().toString();
	
	/**
     * 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：
     *  !#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
     */
	@NotBlank(message = "scene不能为空")
	@ApiModelProperty(value = "场景值（参见微信文档 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html）", example = "p=9462&a=11713&u=3654&test=1", required = true)
    private String scene;
}
