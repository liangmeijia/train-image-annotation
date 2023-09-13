package com.example.trainimageannotation.util.createXml.goods;

import com.example.trainimageannotation.po.Data;
import com.example.trainimageannotation.vo.AnnoSaveVo;
import com.example.trainimageannotation.vo.AnnotationsW3c;

import java.util.List;

/**
 * @description 生成/展示标注文件的接口
 * @author LENOVO
 */
public interface IoperationTag {
    /**
     * 保存标注文件
     * @param annoSaveVo 标注数据
     * @param data 数据集
     * @return
     */
     boolean saveTag(AnnoSaveVo annoSaveVo, Data data);

    /**
     * 展示标注文件
     * @param fileName 文件名
     * @param data 数据集
     * @return
     */
     List<AnnotationsW3c> showXml(String fileName,Data data);
}
