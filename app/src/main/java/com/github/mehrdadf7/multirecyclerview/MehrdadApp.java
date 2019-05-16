package com.github.mehrdadf7.multirecyclerview;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatDelegate;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

public class MehrdadApp extends Application {

  public static ImageLoaderConfiguration config ;
  public static DisplayImageOptions options;

  @Override
  public void onCreate() {
    super.onCreate();

    config = new ImageLoaderConfiguration.Builder(this)
        .denyCacheImageMultipleSizesInMemory()
        .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
        .build();

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

  }
}
