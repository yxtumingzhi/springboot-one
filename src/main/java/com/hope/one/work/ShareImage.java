package com.hope.one.work;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShareImage implements Serializable {
	/**
	 * 分享图地址
	 */
	String shareImgUrl;
	
	/**
	 * 分享图类型（1:卡片,2:海报）
	 */
	Short shareImgType;
}
