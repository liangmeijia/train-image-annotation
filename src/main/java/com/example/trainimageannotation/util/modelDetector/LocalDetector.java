package com.example.trainimageannotation.util.modelDetector;

import java.util.stream.Collectors;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
/**
 * @description 执行本地的python脚本
 * @author LENOVO
 */
public class LocalDetector {
    private static List<String> readOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }

    /**
     * 执行python脚本
     * @param script python脚本地址
     * @param var 传递给python脚本的参数
     * @throws Exception
     */
    public static List<String> detect(String script,String var) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("python3.8",script,var);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        List<String> results = readOutput(process.getInputStream());
        System.out.println(results);
        int exitCode = process.waitFor();
        System.out.println(exitCode);
        return results;
    }
}
