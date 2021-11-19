package com.hope.one.common;

import lombok.Data;

import java.util.Date;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-09-22 10:26
 */
@Data
public class Singleton {

    private String code;


    public Singleton(String code) {
        this.code = code;
    }

}
