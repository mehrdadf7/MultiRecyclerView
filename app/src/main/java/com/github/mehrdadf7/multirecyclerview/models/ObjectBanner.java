package com.github.mehrdadf7.multirecyclerview.models;

public class ObjectBanner {

    private int backgroundColor;
    private String text;

    public ObjectBanner(int backgroundColor, String text) {
        this.backgroundColor = backgroundColor;
        this.text = text;
    }

    public ObjectBanner() {
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
