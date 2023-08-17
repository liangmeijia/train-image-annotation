package com.example.trainimageannotation.createXml.goods;

import com.example.trainimageannotation.vo.AnnoSaveVo;
import com.example.trainimageannotation.vo.AnnotationsW3c;

import java.util.List;

/**
 * @description 生成/展示标注文件的接口
 * @author LENOVO
 */
public interface IOperationTag {
    /**
     * 保存标注文件
     * @param annoSaveVo 标注数据
     * @param dataInPath 图片的位置
     * @param dataOutPath 标注文件保存的位置
     * @return
     */
     boolean saveTag(AnnoSaveVo annoSaveVo, String dataInPath, String dataOutPath);

    /**
     * 展示标注文件
     * @param fileName 文件名
     * @param dataOutPath 标注文件保存的位置
     * @return
     */
     List<AnnotationsW3c> showXml(String fileName,String dataOutPath);
}
