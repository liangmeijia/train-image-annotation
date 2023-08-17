package com.example.trainimageannotation.util.ids.policy;

import com.example.trainimageannotation.util.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @description: 利用 org.apache.commons.lang3.RandomStringUtils生成随即数
 * @author LENOVO
 */
@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
