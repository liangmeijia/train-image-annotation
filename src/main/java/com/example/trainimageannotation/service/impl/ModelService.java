package com.example.trainimageannotation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.dao.DataDao;
import com.example.trainimageannotation.dao.ModelDao;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.po.Model;
import com.example.trainimageannotation.service.IModelService;
import com.example.trainimageannotation.util.CmdExecution;
import com.example.trainimageannotation.util.DownloadUtil;
import com.example.trainimageannotation.util.createXml.factory.OperationTagFactory;
import com.example.trainimageannotation.util.createXml.goods.IoperationTag;
import com.example.trainimageannotation.util.modelDetector.LocalDetector;
import com.example.trainimageannotation.util.modelDetector.RemoteDetector;
import com.example.trainimageannotation.vo.AnnoSaveVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author LENOVO
 * @date 20230814
 */
@Service
public class ModelService implements IModelService {
    @Resource
    private ModelDao modelDao;
    @Resource
    private DataDao dataDao;
    @Resource
    private OperationTagFactory operationTagFactory;
    @Value("${path.remote.in}")
    private String re_dataInPath;
    @Value("${path.remote.out}")
    private String re_dataOutPath ;

    @Override
    public List<Model> showModelList(int offset, int limit) {
        return modelDao.selectModelList(offset,limit);
    }

    @Override
    public Long getModelCount() {
        return modelDao.selectModelCount();
    }

    @Override
    public boolean start (Long modelId, Long dataId) {
        Model model = modelDao.selectModelById(modelId);
        Data data = dataDao.selectDataById(dataId);

        /**
         * 模型权重文件路径
         */
        String modelWeights = model.getModelWeights();
        /**
         * 模型推理文件路径
         */
        String detectPath = model.getCfg();
        /**
         * 模型来源
         */
        String modelSource = model.getSource();
        String dataInPath = data.getDataInPath();
        String dataOutPath = data.getDataOutPath();
        if(modelSource==null){
            try {
                //1.执行
                List<String> detect = LocalDetector.detect(detectPath, "");

                //2.生成标注文件

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            Map parse = (Map)JSONObject.parse(modelSource);
            String host = parse.get("host").toString();
            String port = parse.get("port").toString();
            String user = parse.get("user").toString();
            String password = parse.get("password").toString();

            /**
             * 模型推理的shell命令
             */
            String[] split = dataInPath.split("\\\\");
            String shell ="python3.8  "+detectPath+" --weights "+modelWeights+" --source "+re_dataInPath+"/"+split[split.length-1]+ " --project "+re_dataOutPath+" --save-txt";
            System.out.println(shell);

            try {
                //上传图片
                String uploadCmd = "scp -rP "+port+" "+dataInPath+" "+user+"@"+host+":"+re_dataInPath;
                System.out.println(uploadCmd);
                String s = CmdExecution.exec1(uploadCmd);
                System.out.println(s);
                //【远程】执行推理
                boolean exec = RemoteDetector.exec(shell, host, Integer.valueOf(port), user, password);
                if(exec){
                    //执行shell成功
                    System.out.println(exec);
                    //2.下载检测结果到本地
                    String downCmd = "scp -rP "+port+" "+user+"@"+host+":"+re_dataOutPath+" "+dataOutPath;
                    System.out.println(downCmd);
                    String s1 = CmdExecution.exec1(downCmd);
                    System.out.println(s1);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return false;
    }


}
