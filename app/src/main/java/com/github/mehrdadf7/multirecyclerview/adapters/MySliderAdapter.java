package com.github.mehrdadf7.multirecyclerview.adapters;

import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MySliderAdapter extends SliderAdapter {

    private List<ObjectSlider> banners;

    public MySliderAdapter(List<ObjectSlider> banners) {
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
