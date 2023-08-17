package com.example.trainimageannotation.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadFileUtilTest {
    @Test
    public void TaskTest(){
//        Map<String, String> filesDatas = ReadFileUtil.getFilesDatas("C:\\Users\\LENOVO\\Pictures\\稿件编号_退修--图");
//        System.out.println(filesDatas);
//        ArrayList<File> fileArrayList = new ArrayList<>();
//        ReadFileUtil.getFiles(fileArrayList,"C:\\Users\\LENOVO\\Pictures\\稿件编号_退修--图");
//        System.out.println(fileArrayList);
        List<String> fileName = ReadFileUtil.getFileName("C:\\Users\\LENOVO\\Pictures\\Saved Pictures");
        System.out.println(fileName);
    }
}
