package com.example.trainimageannotation.util;

import java.io.*;

/**
 * @description 执行CMD命令
 * String cmd = "ssh -t -t -p " +port+" "+user+"@"+host;
 * String exec = CmdExecution.exec(cmd,password,shell);
 * @author LENOVO
 */
public class CmdExecution {
    public static String exec1(String cmd1){
        String re ="";
        try {
            /**
             * 调用CMD命令 eg : "ipconfig"
             * /c参数表示执行后关闭CMD窗口
             */
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", cmd1);
            /**
             * 将错误输出流与标准输出流合并
             */
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            /**
             * 获取命令输出结果
             */
            InputStream inputStream = process.getInputStream();
            /**
             * 设置编码为GBK
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                re +=line;
            }
            /**
             * 等待命令执行完成
             */
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return re;
    }
    public static String exec3(String cmd1,String cmd2,String cmd3){
        String re ="";
        try {
            /**
             * 调用CMD命令 eg : "ipconfig"
             * /c参数表示执行后关闭CMD窗口
             */
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", cmd1+"&&"+cmd2+"&&"+cmd3);
            /**
             * 将错误输出流与标准输出流合并
             */
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            /**
             * 获取命令输出结果
             */
            InputStream inputStream = process.getInputStream();
            /**
             * 设置编码为GBK
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                re +=line;
            }
            /**
             * 等待命令执行完成
             */
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return re;
    }
}
