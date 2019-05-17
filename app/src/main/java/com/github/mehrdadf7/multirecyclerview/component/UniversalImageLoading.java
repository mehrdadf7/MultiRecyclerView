package com.github.mehrdadf7.multirecyclerview.component;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.github.mehrdadf7.multirecyclerview.MehrdadApp;
import com.github.mehrdadf7.multirecyclerview.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class UniversalImageLoading implements ImageLoading {

  private DisplayImageOptions options;
  private ImageLoader imageLoader = ImageLoader.getInstance();

  public UniversalImageLoading() {
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(MehrdadApp.getContext())
        .denyCacheImageMultipleSizesInMemory()
        .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
        .build();
    imageLoader.init(config);
  }

  @Override
  public void loadImage(String url, ImageView imageView) {
    options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.mehrdad)
        .showImageForEmptyUri(R.drawable.mehrdad)
        .showImageOnFail(R.drawable.mehrdad)
        .resetViewBeforeLoading(false)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(false)
        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
        .bitmapConfig(Bitmap.Config.ARGB_8888)
        .displayer(new SimpleBitmapDisplayer())
        .build();
    imageLoader.displayImage(url, imageView, options);
  }

  @Override
  public void loadImage(int resource, ImageView imageView) {
    imageLoader.displayImage("android.resource://" + MehrdadApp.getContext().getPackageName() +
        "/" + resource, imageView, options);
  }

  @Override
  public void loadImage(String url, int placeHolder, int errorDrawable, ImageView imageView) {
    options = new DisplayImageOptions.Builder()
        .showImageOnLoading(placeHolder)
        .showImageForEmptyUri(errorDrawable)
        .showImageOnFail(errorDrawable)
        .resetViewBeforeLoading(false)
        .cacheInMemory(true)
        .cacheOnDisk(true)
        .considerExifParams(false)
        .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
        .bitmapConfig(Bitmap.Config.ARGB_8888)
        .displayer(new SimpleBitmapDisplayer())
        .build();
    imageLoader.displayImage(url, imageView, options);
  }

}
