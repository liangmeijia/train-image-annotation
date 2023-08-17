package com.example.trainimageannotation.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SshClientTest {
    @Test
    public void TaskTest(){
        try {
            int res = SshClient.exec("sh" + " " + "/home/lmj/train_image_annotation/" + "hello_world.sh" + " " + "hello world", "123.60.32.152", "root", "1998meijia..");
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
