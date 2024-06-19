package com.example.myapplicationtest1;

public class GetLocationMessage {

    private Integer baseId;
    private String text;

    public GetLocationMessage(Integer baseId, String text) {
        this.baseId = baseId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }
}