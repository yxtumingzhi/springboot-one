package com.hope.one.aop;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-10-29 11:28
 */
@SpringBootTest
public class TestMain {

    @Autowired
    private Demo3 demo3;

    public static void main(String[] args) {
        demo3.bTask();
    }

}
