package com.lunar.demo;


import com.nlf.calendar.Lunar;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class LunarDemoApplicationTests {


    @Test
    void contextLoads() {
        Lunar lunar = new Lunar(1998, 2, 21);
        System.out.println(lunar.toFullString());
        System.out.println(lunar.getSolar().toFullString());

    }

}
