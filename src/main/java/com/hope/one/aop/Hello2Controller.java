package com.hope.one.aop;

import com.alibaba.fastjson.JSONObject;
import com.hope.one.common.BeanConverter;
import com.hope.one.common.HQueryWindTempleResponse;
import com.hope.one.common.WindTempleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class Hello2Controller {

    @PostMapping(value = "/demo_log2")
    public void login(@RequestParam String param) {
        System.out.println(param + "hello!" + "name");
    }

}
