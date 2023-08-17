package com.example.trainimageannotation.util;

import com.example.trainimageannotation.vo.AnnoSaveVo;
import com.example.trainimageannotation.vo.AnnoVo;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Dom4jXml {

    /**
     * 生成xml方法
     */
    public static String createXml(AnnoSaveVo annoSaveVo,String dataInPath,String dataOutPath){
        //1.获取数据
        String fileName = annoSaveVo.getFileName();
        Integer imageWidth=annoSaveVo.getWidth();
        Integer imageHeight=annoSaveVo.getHeight();
        //2.具体保存
        try {
            // 1、创建document对象
            Document document = DocumentHelper.createDocument();
            // 2、创建根节点annotation
            Element annotation = document.addElement("annotation");
            // 3、向annotation节点添加version属性
            //annotation.addAttribute("version", "2.0");
            // 4、生成子节点及子节点内容
            Element folder = annotation.addElement("folder");
            folder.setText("test");

            Element filename = annotation.addElement("filename");
            filename.setText(fileName);

            Element filePath = annotation.addElement("path");
            filePath.setText(dataInPath);

            Element source = annotation.addElement("source");
            Element database =source.addElement("database");
            database.setText("Unknown");
            Element anno =source.addElement("annotation");
            anno.setText("Unknown");
            Element image =source.addElement("image");
            image.setText("Unknown");

            Element imageSize = annotation.addElement("size");
            Element width = imageSize.addElement("width");
            Element height = imageSize.addElement("height");
            Element depth = imageSize.addElement("depth");
            width.setText(String.valueOf(imageWidth));
            height.setText(String.valueOf(imageHeight));
            depth.setText("3");

            Element segmented = annotation.addElement("segmented");
            segmented.setText("0");

            //-------------------图元对象创建开始-------------------------
            //1.保存矩形框
            List<AnnoVo> regions = annoSaveVo.getRegions();
            int size=regions.size()-1;
            while(size>=0){
                Element object = annotation.addElement("object");

                Element label = object.addElement("name");
                label.setText(regions.get(size).getTag());
                //拍摄角度，自己的数据集这里是Unspecified
                Element pose = object.addElement("pose");
                pose.setText("Unspecified");
                //是否被截断，0表示完整未截断
                Element truncated = object.addElement("truncated");
                truncated.setText("0");
                //是否难以识别，0表示不难识别
                Element difficult = object.addElement("difficult");
                difficult.setText("0");

//                Element type = object.addElement("type");
//                type.setText(recList.get(size).getType());
                //创建坐标信息节点
                String location = regions.get(size).getLocation();
                if("rect".equals(regions.get(size).getType())){
                    String[] loc = location.split(",");
                    Element bndbox = object.addElement("bndbox");
                    Element xmin=bndbox.addElement("xmin");
                    xmin.setText(String.valueOf(loc[0]));
                    Element ymin=bndbox.addElement("ymin");
                    ymin.setText(String.valueOf(loc[1]));
                    Element xmax=bndbox.addElement("xmax");
                    xmax.setText(String.valueOf(loc[2]));
                    Element ymax=bndbox.addElement("ymax");
                    ymax.setText(String.valueOf(loc[3]));
                }
                size--;
            }
            //-------------------图元对象创建结束-------------------------
            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");
            // 6、生成xml文件
            File file = new File(dataOutPath);
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            file.createNewFile();
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            //writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("生成"+dataOutPath+"成功");
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成"+dataOutPath+"失败");
            return "false";
        }
    }

//    public  static SavedShapes readXmlByXpath(String _filePath) throws MalformedURLException, DocumentException {
//        //1.处理要初始化加载到内存的xml文件路劲问题，根据相对路径_filePath求得绝对路径filePath
//        String temp=_filePath.split("Annotations")[1];//--->/am/1.xml
//        //String temp1=temp.substring(1);//---->am/1.xml
//        String filePath=path2xml+temp;//--->/home/lmj/image_annotation/resources/Annotations/am/1.xml
//        //2.读取filePath路径中的xml文件，并将其中的内容保存到对象savedShapes中
//        SavedShapes savedShapes = new SavedShapes();
//        savedShapes.setFilePath(_filePath);
//        List<Rec> recList=new ArrayList<>();
//        List<Poly> PolyList=new ArrayList<>();
//        savedShapes.setRecList(recList);
//        savedShapes.setPolyList(PolyList);
//        //1.创建SAXReader实例
//        SAXReader reader=new SAXReader();
//        //2.read()方法读取指定的xml文档并生成dom树
//        Document doc=reader.read(new File(filePath));
//        //3.解析xml
//        List<Node> objectsList = doc.selectNodes("//object");
//        for(Node object:objectsList){
//            Shape shape;
//            String label = object.selectSingleNode("./name").getText();
//            //String type = object.selectSingleNode("./type").getText();
//            //if("rec".equals(type)){
//            Node bndbox = object.selectSingleNode("./bndbox");
//            if(bndbox!=null){
//                shape = new Rec();
//                String x_min = object.selectSingleNode("./bndbox/xmin").getText();
//                String y_min = object.selectSingleNode("./bndbox/ymin").getText();
//                String x_max = object.selectSingleNode("./bndbox/xmax").getText();
//                String y_max = object.selectSingleNode("./bndbox/ymax").getText();
//                shape.setLabel(label);
//                shape.setType("rec");
//                ((Rec) shape).setX_min(Double.valueOf(x_min));
//                ((Rec) shape).setX_max(Double.valueOf(x_max));
//                ((Rec) shape).setY_min(Double.valueOf(y_min));
//                ((Rec) shape).setY_max(Double.valueOf(y_max));
//                recList.add((Rec) shape);
//            }
//
//            // }else if("point".equals(type)){
////                shape=new Point();
////                String x = object.selectSingleNode("./points/x").getText();
////                String y = object.selectSingleNode("./points/y").getText();
////                shape.setLabel(label);
////                shape.setType(type);
////                ((Point) shape).setX(Double.valueOf(x));
////                ((Point) shape).setY(Double.valueOf(y));
////                pointList.add((Point) shape);
//            //}
//
//        }
//        //3.返回
//        return savedShapes;
//    }

}