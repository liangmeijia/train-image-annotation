//package com.example.trainimageannotation.service.impl;
//
//import com.example.trainimageannotation.service.ISshService;
//import com.example.trainimageannotation.util.modelDetector.SshClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class SshService implements ISshService {
//    @Resource
//    private SshClient sshClient;
//    @Value("${myPath.objectdetection.script}")
//    private String path2script;
//
//    @Override
//    public String startSsh(String path2image,String path2xml,String path2weights,String path2cfg,String path2data) {
//        int exec = -1;
//        try {
//            //exec = sshClient.exec("sh"+" "+ "/home/lmj/image_annotation/"+"hello_world.sh"+" "+"hello world");
////            exec = sshClient.exec( path2script+"hello_world.sh"+" "+path2image+" "+path2xml+" "+path2weights+" "+path2cfg+" "+path2data);
//            System.out.println("exec:  "+exec);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return String.valueOf(exec);
//    }
//}
