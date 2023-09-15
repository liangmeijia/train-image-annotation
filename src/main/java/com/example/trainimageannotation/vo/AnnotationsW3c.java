package com.example.trainimageannotation.vo;

import java.util.List;

/**
 * @description 标注显示的信息
 * @author LENOVO
 */
public class AnnotationsW3c {
    private String context ;
    private String id;
    private String type;
    private List<Body> body;
    private Target target;

    public AnnotationsW3c() {
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
