package com.example.trainimageannotation.util;

import com.example.trainimageannotation.util.modelDetector.RemoteDetector;
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
            boolean res = RemoteDetector.exec("sh" + " " + "/home/lmj/train_image_annotation/" + "hello_world.sh" + " " + "hello world", "123.60.32.152", 22,"root", "1998meijia..");
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
