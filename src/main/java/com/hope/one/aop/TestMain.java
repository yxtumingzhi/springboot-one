package com.hope.one.aop;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-29 11:28
 */
public class TestMain {

    public static void main(String[] args) {
        HapiQueryAgentClueParam param = new HapiQueryAgentClueParam();
        param.setGroupId(1);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        param.setPage(pageInfo);

        QueryAgentClueParam a = JSON.parseObject(JSON.toJSONString(param), QueryAgentClueParam.class);

        QueryAgentClueParam b = new QueryAgentClueParam();
        BeanUtils.copyProperties(param, b);

        System.out.println(1);
        //JSON.parseObject(JSON.toJSONString(src), clazz);
    }

}
