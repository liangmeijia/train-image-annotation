package com.example.trainimageannotation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.dao.DataDao;
import com.example.trainimageannotation.dao.ModelDao;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.po.Model;
import com.example.trainimageannotation.service.IModelService;
import com.example.trainimageannotation.util.CmdExecution;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.util.DownloadUtil;
import com.example.trainimageannotation.util.createXml.factory.OperationTagFactory;
import com.example.trainimageannotation.util.createXml.goods.IoperationTag;
import com.example.trainimageannotation.util.modelDetector.LocalDetector;
import com.example.trainimageannotation.util.modelDetector.RemoteDetector;
import com.example.trainimageannotation.vo.AnnoSaveVo;
import com.example.trainimageannotation.vo.Result;
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
    public Result start (Long modelId, Long dataId) {
        Model model = modelDao.selectModelById(modelId);
        Data data = dataDao.selectDataById(dataId);
        Result result = new Result();
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
                //1.执行推理
                List<String> detect = LocalDetector.detect(detectPath, "");

                //2.生成标注文件

            } catch (Exception e) {
                e.printStackTrace();
            }

            result.setCode(Constant.ResponseCode.UN_ERROR.getCode());
            result.setInfo("智能标注失败");

        }else{
            Map parse = (Map)JSONObject.parse(modelSource);
            String host = parse.get("host").toString();
            String port = parse.get("port").toString();
            String user = parse.get("user").toString();
            String password = parse.get("password").toString();

            /**
             * 模型推理的shell命令
             */
            String uploadCmd = "scp -rP "+port+" "+dataInPath+" "+user+"@"+host+":"+re_dataInPath;
            String[] dataInPathArr = dataInPath.split("\\\\");
            String start ="python3.8  "+detectPath+" --weights "+modelWeights+" --source "+re_dataInPath+"/"+dataInPathArr[dataInPathArr.length-1]+ " --project "+re_dataOutPath+" --save-txt";
            String downCmd = "scp -rP "+port+" "+user+"@"+host+":"+re_dataOutPath+" "+dataOutPath;
            System.out.println(start);
            System.out.println(uploadCmd);
            System.out.println(downCmd);
            try {
                //上传图片
                String up = CmdExecution.exec1(uploadCmd);
                System.out.println(up);
                //【远程】执行推理
                boolean exec = RemoteDetector.exec(start, host, Integer.parseInt(port), user, password);
                if(exec){
                    //执行shell成功
                    //下载检测结果到本地
                    String down = CmdExecution.exec1(downCmd);
                    System.out.println(down);
                    //保存结果
                    result.setCode(Constant.ResponseCode.SUCCESS.getCode());
                    result.setInfo("智能标注成功");
                }else {
                    result.setCode(Constant.ResponseCode.UN_ERROR.getCode());
                    result.setInfo("智能标注失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode(Constant.ResponseCode.UN_ERROR.getCode());
                result.setInfo("智能标注失败");
            }
        }
        return result;
    }
}
