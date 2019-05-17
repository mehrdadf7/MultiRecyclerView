package com.github.mehrdadf7.multirecyclerview.component;

import android.widget.ImageView;

import androidx.annotation.DrawableRes;

public interface ImageLoading {
  void loadImage(String url, ImageView imageView);
  void loadImage(@DrawableRes int resource, ImageView imageView);
  void loadImage(String url, @DrawableRes int placeHolder,
                 @DrawableRes int errorDrawable, ImageView imageView);
}
