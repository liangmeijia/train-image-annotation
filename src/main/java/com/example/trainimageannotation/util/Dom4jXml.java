//package com.example.trainimageannotation.util;
//
//import com.example.springboot.domain.Point;
//import com.example.springboot.domain.Poly;
//import com.example.springboot.domain.Rec;
//import com.example.springboot.domain.Shape;
//import com.example.springboot.domain.vo.SavedShapes;
//import com.example.trainimageannotation.vo.AnnoVo;
//import org.dom4j.*;
//import org.dom4j.io.OutputFormat;
//import org.dom4j.io.SAXReader;
//import org.dom4j.io.XMLWriter;
//import org.springframework.beans.factory.annotation.Value;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.net.MalformedURLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Dom4jXml {
//    @Value("${myPath.anno}")
//    private String path2xml;
//    /**
//     * 生成xml方法
//     */
//    public static String createXml(String filePathStr, String filePath2xml, List<AnnoVo> recAnnoVoList){
//        //0.获取数据
//        List<Rec> recList= savedShapes.getRecList();
//        String imageW="1024";
//        String imageH="1024";
//        //1.接收需要保存标签的图片相对路径，最终将其保存到filePath路径中
//        String name1=filePathStr.split("JPEGImages")[1];//--->  /am/5.jpg
//        //2.具体保存
//        try {
//            // 1、创建document对象
//            Document document = DocumentHelper.createDocument();
//            // 2、创建根节点annotation
//            Element annotation = document.addElement("annotation");
//            // 3、向annotation节点添加version属性
//            //annotation.addAttribute("version", "2.0");
//            // 4、生成子节点及子节点内容
//            Element folder = annotation.addElement("folder");
//            folder.setText("test");
//
//            Element filename = annotation.addElement("filename");
//            String[] filename_split = name1.split("/");
//            filename.setText();
//
//            Element filePath = annotation.addElement("path");
//            filePath.setText(filePathStr);
//
//            Element source = annotation.addElement("source");
//            Element database =source.addElement("database");
//            database.setText("Unknown");
//
//            Element imageSize = annotation.addElement("size");
//            Element width = imageSize.addElement("width");
//            Element height = imageSize.addElement("height");
//            Element depth = imageSize.addElement("depth");
//            width.setText(imageW);
//            height.setText(imageH);
//            depth.setText("3");
//
//            Element segmented = annotation.addElement("segmented");
//            segmented.setText("0");
//
//            //-------------------图元对象创建开始-------------------------
//            //1.保存矩形框
//            int size2recList=recList.size()-1,size2polyList=polyList.size()-1;
//            while(size2recList>=0){
//                Element object = annotation.addElement("object");
//
//                Element label = object.addElement("name");
//                label.setText(recList.get(size2recList).getLabel());
//
//                Element pose = object.addElement("pose");
//                pose.setText("Unspecified");
//
//                Element truncated = object.addElement("truncated");
//                truncated.setText("0");
//
//                Element difficult = object.addElement("difficult");
//                difficult.setText("0");
//
////                Element type = object.addElement("type");
////                type.setText(recList.get(size).getType());
//                //创建点坐标信息节点
//                //if("rec".equals(recList.get(size2recList).getType())){
//                    Element bndbox = object.addElement("bndbox");
//                    Element x_min=bndbox.addElement("xmin");
//                    x_min.setText(String.valueOf(recList.get(size2recList).getX_min()));
//                    Element y_min=bndbox.addElement("ymin");
//                    y_min.setText(String.valueOf(recList.get(size2recList).getY_min()));
//                    Element x_max=bndbox.addElement("xmax");
//                    x_max.setText(String.valueOf(recList.get(size2recList).getX_max()));
//                    Element y_max=bndbox.addElement("ymax");
//                    y_max.setText(String.valueOf(recList.get(size2recList).getY_max()));
//                //}
//                size2recList--;
//            }
//            //2.保存多边形
//            while(size2polyList>=0){
//                Element object = annotation.addElement("object");
//
//                Element label = object.addElement("name");
//                label.setText(polyList.get(size2polyList).getLabel());
//
//                Element pose = object.addElement("pose");
//                pose.setText("Unspecified");
//
//                Element truncated = object.addElement("truncated");
//                truncated.setText("0");
//
//                Element difficult = object.addElement("difficult");
//                difficult.setText("0");
//
////                Element type = object.addElement("type");
////                type.setText(recList.get(size).getType());
//                //创建点坐标信息节点
//                //if("rec".equals(recList.get(size2polyList).getType())){
//                    Element polybox = object.addElement("polybox");
//                    int pointSize=polyList.get(size2polyList).getPoints().size()-1;
//                    while(pointSize>=0){
//                        Element point=polybox.addElement("point");
//                        Element x=point.addElement("x");
//                        Point point1 = polyList.get(size2polyList).getPoints().get(pointSize);
//                        Double x1 = point1.getX();
//                        x.setText(String.valueOf(x1));
//                        Element y=point.addElement("y");
//                        Double y1 = point1.getY();
//                        y.setText(String.valueOf(y1));
//                        pointSize--;
//                    }
//
//                //}
//                size2polyList--;
//            }
//            //-------------------图元对象创建结束-------------------------
//            // 5、设置生成xml的格式
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            // 设置编码格式
//            format.setEncoding("UTF-8");
//            // 6、生成xml文件
//            File file = new File(filePath2xml);
//            File fileParent = file.getParentFile();
//            if(!fileParent.exists()){
//                fileParent.mkdirs();
//            }
//            file.createNewFile();
//            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
//            // 设置是否转义，默认使用转义字符
//            //writer.setEscapeText(false);
//            writer.write(document);
//            writer.close();
//            System.out.println("生成"+filePath2xml+"成功");
//            return "true";
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("生成"+filePath2xml+"失败");
//            return "false";
//        }
//    }
//
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
//           // }else if("point".equals(type)){
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
////    public  String getWebFile(){
////        //win:   C:\task\image_annotation_project\resources\Annotations\
////        //linux:  /home/lmj/image_annotation/resources/Annotations/
////        return " C:\\task\\image_annotation_project\\resources\\Annotations\\";
////    }
//}