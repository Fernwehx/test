package com.lunar.demo.demos.staterdemo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: hehsmk
 * @Date: 2023/12/29 16:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student {

    @Excel(name = "姓名",width = 10)
    private String name;

    @Excel(name = "性别",width = 10)
    private String sex;

    @Excel(name = "年龄",width = 10)
    private Integer age;
}
