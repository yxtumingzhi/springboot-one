package com.hope.one.aop;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.hope.one.common.BeanConverter;
import com.hope.one.common.HQueryWindTempleResponse;
import com.hope.one.common.WindTempleResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/hello")
@RestController
@RequestMapping(value = "/partner", method = RequestMethod.POST)
@ResponseBody
public class HelloController {

    private WindTempleResponse fsWindTempleConfig;
    private HQueryWindTempleResponse config = new HQueryWindTempleResponse();

    @Value("${windTemple.config}")
    private void setFsWindTempleConfig(String config) {
        this.fsWindTempleConfig = JSONObject.parseObject(config, WindTempleResponse.class);
        BeanConverter.convert(fsWindTempleConfig, HQueryWindTempleResponse.class);
    }

    @GetMapping("/demo_log")
    public void login() {

        System.out.println(String.format("kjkk", "asdf"));
        System.out.println("hello!" + "name");
    }

    @RequestMapping("/login/{name}")
    @MyAnnotation(methodName = "login")
    public void login(@PathVariable String name) {
        System.out.println("hello!" + name);
    }
}
