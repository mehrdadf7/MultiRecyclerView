package com.github.mehrdadf7.multirecyclerview.models;

public class ObjectItem {

  private String parentTitle;
  private String text;
  private int layoutManager;

  public ObjectItem(String parentTitle, String text) {
    this.parentTitle = parentTitle;
    this.text = text;
  }

  public ObjectItem() {
  }

  public String getParentTitle() {
    return parentTitle;
  }

  public void setParentTitle(String parentTitle) {
    this.parentTitle = parentTitle;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getLayoutManager() {
    return layoutManager;
  }

  public void setLayoutManager(int layoutManager) {
    this.layoutManager = layoutManager;
  }
}
