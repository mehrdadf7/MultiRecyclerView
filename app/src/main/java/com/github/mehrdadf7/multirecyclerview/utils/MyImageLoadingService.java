package com.github.mehrdadf7.multirecyclerview.utils;

import android.widget.ImageView;

import com.github.mehrdadf7.multirecyclerview.MehrdadApp;
import com.github.mehrdadf7.multirecyclerview.component.ImageLoadingInjector;

import ss.com.bannerslider.ImageLoadingService;

public class MyImageLoadingService implements ImageLoadingService {

  @Override
  public void loadImage(String url, ImageView imageView) {
    ImageLoadingInjector.getImageLoading().loadImage(url, imageView);
  }

  @Override
  public void loadImage(int resource, ImageView imageView) {
    ImageLoadingInjector.getImageLoading()
        .loadImage("android.resource://" + MehrdadApp.getContext().getPackageName() + "/" + resource, imageView);
  }

  @Override
  public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
    ImageLoadingInjector.getImageLoading().loadImage(url, placeHolder, errorDrawable, imageView);
  }

}
