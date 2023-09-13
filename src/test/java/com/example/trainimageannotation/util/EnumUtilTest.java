package com.example.trainimageannotation.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumUtilTest {

    @Test
    public void TaskTest(){
        String nameByCode = Constant.TaskStatus.getInfoByCode(0);
        System.out.println(nameByCode);
        Constant.TagWay enumByCode = Constant.TagWay.getEnumByCode(0);
        System.out.println(enumByCode.toString());
    }
}
