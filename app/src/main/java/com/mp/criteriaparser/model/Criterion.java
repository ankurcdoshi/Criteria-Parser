package com.mp.criteriaparser.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Criterion implements Serializable {

    private String type;
    private String text;
    private Map<String, CustomisableParameter> variable = new HashMap<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, CustomisableParameter> getVariable() {
        return variable;
    }

    public void setVariable(Map<String, CustomisableParameter> variable) {
        this.variable = variable;
    }
}
