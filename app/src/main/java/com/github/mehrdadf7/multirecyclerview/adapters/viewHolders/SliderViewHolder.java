package com.github.mehrdadf7.multirecyclerview.adapters.viewHolders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.adapters.child.MySliderAdapter;
import com.github.mehrdadf7.multirecyclerview.databinding.ViewSliderBinding;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.utils.MyImageLoadingService;

import java.util.ArrayList;

import ss.com.bannerslider.Slider;

public class SliderViewHolder extends RecyclerView.ViewHolder {

  //view_slider
  private ViewSliderBinding binding;

  public SliderViewHolder(@NonNull ViewSliderBinding binding) {
    super(binding.getRoot());
    this.binding = binding;
  }

  public void bind(ArrayList<ObjectSlider> sliders) {
    Slider.init(new MyImageLoadingService());
    MySliderAdapter mySliderAdapter = new MySliderAdapter(sliders);
    binding.setAdapter(mySliderAdapter);
  }

}
