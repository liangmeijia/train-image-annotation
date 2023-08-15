package com.example.trainimageannotation.service;

import com.example.trainimageannotation.po.Task;
import com.example.trainimageannotation.vo.TaskVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
    @Resource
    private ITaskService taskService;

    @Test
    public void TaskTest(){
        List<TaskVo> taskVoListList = taskService.showTaskVoList(0,10);
        for (TaskVo taskVo :taskVoListList){
            System.out.println(taskVo);
        }

    }
}
