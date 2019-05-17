package com.github.mehrdadf7.multirecyclerview.adapters.child;

import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;

import java.util.ArrayList;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MySliderAdapter extends SliderAdapter {

  private ArrayList<ObjectSlider> banners;

  public MySliderAdapter(ArrayList<ObjectSlider> banners) {
    this.banners = banners;
  }

  @Override
  public int getItemCount() {
    return banners.size();
  }

  @Override
  public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
    viewHolder.bindImageSlide(banners.get(position).getImageUrl());
  }
}
