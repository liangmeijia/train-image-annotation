package com.example.trainimageannotation.vo;

import java.util.List;

public class Target {
    private List<Selector> selector;
    private RenderedVia renderedVia;

    public Target() {
    }

    public List<Selector> getSelector() {
        return selector;
    }

    public void setSelector(List<Selector> selector) {
        this.selector = selector;
    }

    public RenderedVia getRenderedVia() {
        return renderedVia;
    }

    public void setRenderedVia(RenderedVia renderedVia) {
        this.renderedVia = renderedVia;
    }
}

