package com.github.mehrdadf7.multirecyclerview.component;

public class ImageLoadingInjector {
  public static ImageLoading getImageLoading() {
    return new UniversalImageLoading();
  }
}
