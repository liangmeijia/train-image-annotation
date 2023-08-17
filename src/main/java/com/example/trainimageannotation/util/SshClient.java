package com.example.trainimageannotation.util;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author LENOVO
 */
public class SshClient {
    private static String charset = Charset.defaultCharset().toString();
    private static final int TIME_OUT = 1000 * 5 * 60;
    private static Connection conn;

    public SshClient() {
    }

    /**
     * 登录远程服务器
     * @param ip 远程服务器ip
     * @param username 登录名
     * @param password 登录密码
     * @return
     * @throws IOException
     */
    private static boolean login(String ip, String username,String password) throws IOException {
        conn = new Connection(ip);
        conn.connect();
        return conn.authenticateWithPassword(username, password);
    }

    /**
     * 执行shell脚本
     * @param shell shell脚本
     * @param ip 服务器ip
     * @param username 登录名
     * @param password 登录密码
     * @return
     * @throws Exception
     */
    public  static int exec(String shell,String ip, String username,String password) throws Exception {
        //Map<String,Object> map=new HashMap<>();
        int ret = -1;
        StringBuilder sb = new StringBuilder();
        try {
            if (login(ip,username,password)) {
                Session session = conn.openSession();
                session.execCommand(shell);
                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
                InputStream stdout = session.getStdout();
                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                while (true)
                {
                    String line = br.readLine();
                    if (line == null){
                        break;
                    }
                    sb.append(line+"  ");
                }
                System.out.println(sb.toString());
                //map.put("result",sb.toString());
                ret = session.getExitStatus();
               // map.put("num",ret);
            } else {
                throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return ret;
    }

//    public  String exec1(String shell) throws Exception {
//        StringBuilder sb = new StringBuilder();
//        try {
//            if (login()) {
//                Session session = conn.openSession();
//                session.execCommand(shell);
//                session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
//                InputStream stdout = session.getStdout();
//                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
//                while (true)
//                {
//                    String line = br.readLine();
//                    if (line == null)
//                        break;
//                    sb.append(line+"  ");
//                }
//            } else {
//                throw new Exception("登录远程机器失败" + ip); // 自定义异常类 实现略
//            }
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//        return sb.toString();
//    }

}