package com.hope.one.work;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-12 15:58
 */
public class Work {

    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static final String BUSINESS_CARD_IMG_URL_TEMPLATE = "https://lcbqiniu.lechebangstatic.com/micro/poster_tp/user_card_v1.png?" +
            "watermark/3" +
            "/image/%s/%s/%s";

//    public List<ShareImage> getAgentWindTempleShareImg(BaseShareImgReq req) {
//        List<ShareImage> result = Lists.newArrayList();
//        try {
//            //小程序码
//            String wxaCodeImgUrl = getWXACodeUnlimit(req.getStoreId(), req.getPage(), req.getScene(), false);
//            //添加海报水印，数据库直接存带七牛云水印路径规则的URL，这里只替换URL上的参数
//            WindTempleResponse response = windTempleService.getWindTemple();
//            List<String> shareImages = response.getShareImageList();
//            for (String shareImage : shareImages) {
//                result.add(getShareImage(shareImage, wxaCodeImgUrl));
//            }
//        } catch (Exception e) {
//            log.error("获取合伙人分享图方法异常, request:{}, exception:{}", req, e);
//        }
//        return result;
//    }

    public static List<ShareImage> getAgentActivityShareImg() {

        List<String> images = Lists.newArrayList(BUSINESS_CARD_IMG_URL_TEMPLATE);

        List<ShareImage> result = Lists.newArrayList();

            //小程序码
            //添加海报水印，数据库直接存带七牛云水印路径规则的URL，这里只替换URL上的参数
            for (String image : images) {
                result.add(getShareImage(image, "wxaCodeImgUrl", "sf", "sf"));
            }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(getAgentActivityShareImg()));
    }

    /**
     * @param template 七牛云图片
     * @param var      水印参数
     * @return 分享图
     */
    public static ShareImage getShareImage(String template, String... var) {
        ShareImage image = new ShareImage();
        image.setShareImgType((short) 2);
        image.setShareImgUrl(String.format(template, getEncoder(var)));
        return image;
    }

    /**
     * @param var 水印参数
     * @return url safe base64 encode result
     */
    public static Object[] getEncoder(String... var) {
        List<String> result = Lists.newArrayList();
        for (String s : var) {
            result.add(base64Encoder.encodeToString(s.getBytes(StandardCharsets.UTF_8)));
        }
        //String[] a = new String[]{};
        return result.toArray();
    }


}
