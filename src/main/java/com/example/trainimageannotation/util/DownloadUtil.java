package com.example.trainimageannotation.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
/**
 * @description 从远程服务器上下载文件到本地
 * @author LENOVO
 */
public class DownloadUtil {
    /**
     * 下载远程文件并保存到本地
     *
     * @param remoteFilePath-远程文件路径  "https://tenfei01.cfp.cn/creative/vcg/800/new/VCG211157640278-VXD.jpg"
     * @param localFilePath-本地文件路径（带文件名） "E:\\LeStoreDownload\\update\\广州塔.jpg"
     */
    public static void downloadFile(String remoteFilePath, String localFilePath) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try {
            urlfile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
