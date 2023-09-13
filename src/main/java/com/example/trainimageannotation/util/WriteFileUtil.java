package com.example.trainimageannotation.util;

import java.io.*;

/**
 * @author LENOVO
 */
public class WriteFileUtil {
    /**
     *
     * @param content 内容
     * @param filePath 文件位置
     */
    public static void writeReport(String content,String filePath) {
        BufferedWriter out = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            //编码格式可自己更换
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true), "UTF-8"));
            out.write(content + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();

            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
