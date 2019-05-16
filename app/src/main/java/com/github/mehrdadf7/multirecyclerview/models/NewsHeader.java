package com.github.mehrdadf7.multirecyclerview.models;

import java.io.Serializable;

public class NewsHeader implements Serializable {

  private String category;

  public NewsHeader(String category) {
    this.category = category;
  }

  public NewsHeader() {
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
