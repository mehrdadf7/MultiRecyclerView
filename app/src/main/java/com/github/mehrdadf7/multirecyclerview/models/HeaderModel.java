package com.github.mehrdadf7.multirecyclerview.models;

import java.io.Serializable;

public class HeaderModel implements Serializable {

  private String category;

  public HeaderModel(String category) {
    this.category = category;
  }

  public HeaderModel() {
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
