package com.example.trainimageannotation.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.io.File;
import java.io.IOException;
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
//        List<String> fileName = ReadFileUtil.getFileName("C:\\Users\\LENOVO\\Pictures\\Saved Pictures");
//        System.out.println(fileName);
        try {
            String file = ReadFileUtil.readFile(new File("C:\\Users\\LENOVO\\Pictures\\Saved Pictures\\2b27dac8cc647f8aac989da2d1166db2.xml"));
            String[] split = file.split("\\r\\n");
            for (String s : split){
                String[] s1 = s.split(" ");

            }
            System.out.println(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
