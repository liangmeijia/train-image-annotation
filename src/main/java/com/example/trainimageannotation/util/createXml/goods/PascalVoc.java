package com.example.trainimageannotation.util.createXml.goods;

import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.util.Constant;
import com.example.trainimageannotation.util.ids.IIdGenerator;
import com.example.trainimageannotation.vo.*;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LENOVO
 */
@Component
public class PascalVoc implements IoperationTag {
    @Resource
    private Map<Constant.Ids, IIdGenerator> idGenerator;

    @Override
    public boolean saveTag(AnnoSaveVo annoSaveVo,Data data) {
        //1.获取数据
        String dataInPath = data.getDataInPath();
        String dataOutPath = data.getDataOutPath();
        String fileName = annoSaveVo.getFileName();
        Integer imageWidth=annoSaveVo.getWidth();
        Integer imageHeight=annoSaveVo.getHeight();
        String[] split = fileName.split("\\.");
        String saveXml = dataOutPath+"\\"+split[0]+".xml";

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
            folder.setText(dataInPath);

            Element filename = annotation.addElement("filename");
            filename.setText(fileName);

            Element source = annotation.addElement("source");
            Element database =source.addElement("database");
            database.setText("Unknown");
            Element anno =source.addElement("annotation");
            anno.setText("Unknown");
            Element image =source.addElement("image");
            image.setText("Unknown");

            Element imageSize = annotation.addElement("size");
            Element image_Width = imageSize.addElement("width");
            Element image_Height = imageSize.addElement("height");
            Element depth = imageSize.addElement("depth");
            image_Width.setText(String.valueOf(imageWidth));
            image_Height.setText(String.valueOf(imageHeight));
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
                    Double xmin = Double.valueOf(loc[0]);
                    Double ymin = Double.valueOf(loc[1]);
                    Double width = Double.valueOf(loc[2]);
                    Double height = Double.valueOf(loc[3]);
                    Double xmax = xmin+width;
                    Double ymax = ymin+height;

                    Element bndbox = object.addElement("bndbox");
                    Element x_min=bndbox.addElement("xmin");
                    x_min.setText(xmin.toString());
                    Element y_min=bndbox.addElement("ymin");
                    y_min.setText(ymin.toString());
                    Element x_max=bndbox.addElement("xmax");
                    x_max.setText(xmax.toString());
                    Element y_max=bndbox.addElement("ymax");
                    y_max.setText(ymax.toString());
                }
                size--;
            }
            //-------------------图元对象创建结束-------------------------
            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");
            // 6、生成xml文件
            File file = new File(saveXml);
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
            System.out.println("生成"+saveXml+"成功");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<AnnotationsW3c> showXml(String fileName, Data data ) {
        String dataOutPath = data.getDataOutPath();

        List<AnnotationsW3c> annotationsW3cList = new ArrayList<>();
        String[] split = fileName.split("\\.");
        String saveXml = dataOutPath+"\\"+split[0]+".xml";

        //1.创建SAXReader实例
        SAXReader reader=new SAXReader();
        //2.read()方法读取指定的xml文档并生成dom树
        Document doc= null;
        try {
            doc = reader.read(new File(saveXml));
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
        //3.解析xml
        List<Node> objectsList = doc.selectNodes("//object");
        for(Node object:objectsList){
            AnnotationsW3c annotationsW3c = new AnnotationsW3c();

            String label = object.selectSingleNode("./name").getText();
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
            Node bndbox = object.selectSingleNode("./bndbox");
            String val = "";
            if(bndbox!=null){
                String x_min = object.selectSingleNode("./bndbox/xmin").getText();
                String y_min = object.selectSingleNode("./bndbox/ymin").getText();
                String x_max = object.selectSingleNode("./bndbox/xmax").getText();
                String y_max = object.selectSingleNode("./bndbox/ymax").getText();
                Double width = Double.parseDouble(x_max) - Double.parseDouble(x_min);
                Double height =  Double.parseDouble(y_max) - Double.parseDouble(y_min);
                val = "xywh=pixel:"+x_min+","+y_min+","+width+","+height;

            }
            selector.setValue(val);
            selectorList.add(selector);
            target.setSelector(selectorList);

            annotationsW3c.setTarget(target);
            annotationsW3cList.add(annotationsW3c);
        }
        //3.返回
        return annotationsW3cList;
    }
}
