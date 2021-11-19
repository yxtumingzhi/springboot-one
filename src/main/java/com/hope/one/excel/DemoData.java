package com.hope.one.excel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author tumingzhi
 * @version 1.0
 * @date 2021-11-04 9:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData {

    @ExcelProperty("字符串标题")
    private String string;

    @ExcelProperty("日期标题")
    private Date date;

    @ExcelProperty("数字标题")
    private Double doubleData;

    @ExcelIgnore
    private String ignore;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Demo1 {
        private String key;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Demo2 {
        private String hello;
    }
}