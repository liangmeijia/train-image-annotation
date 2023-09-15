package com.example.trainimageannotation.util.createXml.goods;

import com.alibaba.fastjson.JSONObject;
import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.service.impl.TaskService;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.util.ReadFileUtil;
import com.example.trainimageannotation.util.WriteFileUtil;
import com.example.trainimageannotation.util.ids.IIdGenerator;
import com.example.trainimageannotation.vo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LENOVO
 */
@Component
public class YOLO implements IoperationTag{
    @Resource
    private TaskService taskService;
    @Resource
    private Map<Constant.Ids, IIdGenerator> idGenerator;

    @Override
    public boolean saveTag(AnnoSaveVo annoSaveVo, Data data) {
        //1.获取数据
        String dataOutPath = data.getDataOutPath();
        Integer imageWidth=annoSaveVo.getWidth();
        Integer imageHeight=annoSaveVo.getHeight();
        String fileName = annoSaveVo.getFileName();
        String[] fileNameArr = fileName.split("\\.");
        String saveTxt = dataOutPath+"\\"+fileNameArr[0]+".txt";
        String currentTaskId = annoSaveVo.getCurrentTaskId();
        Task task = taskService.showTaskById(Long.valueOf(currentTaskId));
        //tag
        Map<String, Integer> tagMap = getTagMap2save(task);
        //saveTxt文件存在，则删除
        File file = new File(saveTxt);
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                return false;
            }
        }
        //1.保存矩形框
        List<AnnoVo> regions = annoSaveVo.getRegions();
        int size=regions.size()-1;
        while(size>=0){
            String content = "";
            //保存类别信息节点
            Integer label = tagMap.get(regions.get(size).getTag());
            content +=label+" ";
            //保存坐标信息节点
            String location = regions.get(size).getLocation();
            if("rect".equals(regions.get(size).getType())){
                String[] loc = location.split(",");
                String[] yolo = xywh2yolo(imageWidth.toString(), imageHeight.toString(), loc[0], loc[1], loc[2], loc[3]);
                content+=yolo[0]+" "+yolo[1]+" "+yolo[2]+" "+yolo[3];
            }
            WriteFileUtil.writeReport(content,saveTxt);
            size--;
        }
            return true;
    }

    @Override
    public List<AnnotationsW3c> showXml(String fileName, Task task,Data data) {
        String dataInPath = data.getDataInPath();
        String dataOutPath = data.getDataOutPath();

        List<AnnotationsW3c> annotationsW3cList = new ArrayList<>();
        String[] split = fileName.split("\\.");
        String saveXml = dataOutPath+"\\"+split[0]+".txt";
        //tag
        Map<Integer,String> tagMap = getTagMap2show(task);

        try {
            String readFile = ReadFileUtil.readFile(new File(saveXml));
            if(readFile == null ){
                return null;
            }
            String[] objectsList = readFile.split("\\n");

            for(String object:objectsList){
                AnnotationsW3c annotationsW3c = new AnnotationsW3c();
                String[] anno = object.split(" ");
                String label = tagMap.get(Integer.valueOf(anno[0]));

                annotationsW3c.setId(String.valueOf(idGenerator.get(Constant.Ids.SnowFlake).nextId()));
                annotationsW3c.setType("Annotation");
                annotationsW3c.setContext("http://www.w3.org/ns/anno.jsonld");
                List<Body> bodyList = new ArrayList<>();
                Body body = new Body();
                body.setType("TextualBody");
                body.setPurpose("tagging");
                //设置标签
                body.setValue(label);
                bodyList.add(body);
                annotationsW3c.setBody(bodyList);

                Target target = new Target();
                List<Selector> selectorList = new ArrayList<>();
                Selector selector =  new Selector();
                selector.setType("FragmentSelector");
                selector.setConformsTo("http://www.w3.org/TR/media-frags/");
                String val = "";
                if(anno.length==5){
                    File file = new File(dataInPath+"\\"+fileName);
                    BufferedImage image = ImageIO.read(file);
                    // 获取图片的宽度和高度
                    int imageWidth = image.getWidth();
                    int imageHeight = image.getHeight();

                    String[] arr = yolo2xywh(String.valueOf(imageWidth), String.valueOf(imageHeight), anno[1], anno[2], anno[3], anno[4]);
                    val = "xywh=pixel:"+arr[0]+","+arr[1]+","+arr[2]+","+arr[3];

                }
                selector.setValue(val);
                selectorList.add(selector);
                target.setSelector(selectorList);

                annotationsW3c.setTarget(target);
                annotationsW3cList.add(annotationsW3c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //3.返回
        return annotationsW3cList;
    }

    /**
     * 标签保存对应关系
     * @param task 任务
     * @return
     */
    public Map<String,Integer> getTagMap2save(Task task){
        String tag = task.getTag();
        String[] tagArr = tag.split("\\,");
        Map<String,Integer> tagMap = new HashMap<>(tagArr.length);
        for (int i=0;i<tagArr.length;i++){
            tagMap.put(tagArr[i],i);
        }
        return  tagMap;
    }

    /**
     * 标签显示对应关系
     * @param task 任务
     * @return
     */
    public Map<Integer,String> getTagMap2show(Task task){
        String tag = task.getTag();
        String[] tagArr = tag.split("\\,");
        Map<Integer,String> tagMap = new HashMap<>(tagArr.length);
        for (int i=0;i<tagArr.length;i++){
            tagMap.put(i,tagArr[i]);
        }
        return  tagMap;
    }

    /**
     * 正常显示坐标格式转yolo
     * @param imageWidth 图片宽度
     * @param imageHeight 图片高度
     * @param xmin 图元左上角x坐标
     * @param ymin 图元左上角y坐标
     * @param width 图元宽度
     * @param height 图元高度
     * @return yolo[0] 图元中心点的x坐标/图片的宽度、yolo[1] 图元中心点的y坐标/图片的高度 、yolo[2] 图元宽度/图片宽度、 yolo[3] 图元高度/图片高度
     */
    private String[] xywh2yolo(String imageWidth,String imageHeight,String xmin,String ymin,String width,String height){
        String[] yolo = new String[4];
        Double xmin1 = Double.valueOf(xmin);
        Double ymin1 = Double.valueOf(ymin);
        Double width1 = Double.valueOf(width);
        Double height1 = Double.valueOf(height);
        Double xmax1 = xmin1+width1;
        Double ymax1 = ymin1+height1;
        Double imageWidth1 = Double.valueOf(imageWidth);
        Double imageHeight1 = Double.valueOf(imageHeight);

        yolo[0]= String.valueOf(((xmin1 +xmax1)/2/imageWidth1));
        yolo[1]= String.valueOf((ymin1 + ymax1)/2/imageHeight1);
        yolo[2]= String.valueOf(width1/imageWidth1);
        yolo[3]= String.valueOf(height1/imageHeight1);
        return yolo;
    }

    /**
     * yolo坐标格式转正常显示格式
     * @param norm_x
     * @param norm_y
     * @param norm_w
     * @param norm_h
     * @return
     */
    private String[] yolo2xywh(String imageWidth,String imageHeight,String norm_x,String norm_y,String norm_w,String norm_h){
        String[] arr = new String[4];
        Double norm_x1 = Double.valueOf(norm_x);
        Double norm_y1 = Double.valueOf(norm_y);
        Double norm_w1 = Double.valueOf(norm_w);
        Double norm_h1 = Double.valueOf(norm_h);
        Double imageWidth1 = Double.valueOf(imageWidth);
        Double imageHeight1 = Double.valueOf(imageHeight);

        Double xmin=imageWidth1 * (norm_x1 - 0.5 * norm_w1);
        Double ymin=imageHeight1 * (norm_y1 - 0.5 * norm_h1);
        Double xmax=imageWidth1 * (norm_x1 + 0.5 * norm_w1);
        Double ymax=imageHeight1 * (norm_y1 + 0.5 * norm_h1);
        Double width = xmax-xmin;
        Double height = ymax-ymin;

        arr[0] = xmin.toString();
        arr[1] = ymin.toString();
        arr[2] = width.toString();
        arr[3] = height.toString();
        return arr;
    }
}
