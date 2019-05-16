package com.github.mehrdadf7.multirecyclerview.utils;

import android.content.Context;
import android.widget.ImageView;

import com.github.mehrdadf7.multirecyclerview.MehrdadApp;
import com.nostra13.universalimageloader.core.ImageLoader;

import ss.com.bannerslider.ImageLoadingService;

public class PicassoImageLoadingService implements ImageLoadingService {

  private ImageLoader imageLoader = ImageLoader.getInstance();
  private Context context;

  public PicassoImageLoadingService(Context context) {
    this.context = context;
  }

  @Override
  public void loadImage(String url, ImageView imageView) {
    imageLoader.init(MehrdadApp.config);
    imageLoader.displayImage(url, imageView, MehrdadApp.options);
  }

  @Override
  public void loadImage(int resource, ImageView imageView) {
    imageLoader.init(MehrdadApp.config);
    imageLoader.displayImage("android.resource://" + context.getPackageName() + "/" + resource, imageView, MehrdadApp.options);
  }

  @Override
  public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
    imageLoader.init(MehrdadApp.config);
    imageLoader.displayImage(url, imageView, MehrdadApp.options);
  }

}
