package com.example.trainimageannotation.util.modelDetector;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @description 执行远程服务器上的shell脚本
 * @author LENOVO
 */
public class RemoteDetector {
    private static String charset = Charset.defaultCharset().toString();
    private static final int TIME_OUT = 1000  * 60* 5;
    private static Connection conn;

    public RemoteDetector() {
    }

    /**
     * 连接远程服务器
     * @param ip 远程服务器ip
     * @param port 远程服务端口
     * @param username 登录名
     * @param password 登录密码
     * @return
     * @throws IOException
     */
    private static boolean login(String ip, int port,String username,String password) throws IOException {
        conn = new Connection(ip,port);
        conn.connect();
        return conn.authenticateWithPassword(username, password);
    }

    /**
     * 执行shell脚本
     * @param shell shell脚本
     * @param ip 服务器ip
     * @param port 远程服务端口
     * @param user 登录名
     * @param password 登录密码
     * @return
     * @throws Exception
     */
        public static boolean exec(String shell,String ip, int port,String user,String password) throws Exception {
        boolean ret = false;
        StringBuilder sb = new StringBuilder();
        try {
            if (login(ip,port,user,password)) {
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
                    sb.append(line+"\n");
                }
                System.out.println(sb.toString());
                ret = session.getExitStatus()==0;
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
}