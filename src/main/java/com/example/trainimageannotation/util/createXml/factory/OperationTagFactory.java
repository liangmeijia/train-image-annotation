package com.example.trainimageannotation.util.createXml.factory;

import com.example.trainimageannotation.util.createXml.goods.IoperationTag;
import org.springframework.stereotype.Service;

/**
 * @description: 配送商品简单工厂，提供获取配送服务
 * @author: 小傅哥，微信：fustack
 * @date: 2021/9/4
 * @github: https://github.com/fuzhengwei
 * @Copyright: 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Service
public class OperationTagFactory extends GoodsConfig {

    public IoperationTag getOperationTagService(Integer TagWay){
        return operationTagMap.get(TagWay);
    }

}
