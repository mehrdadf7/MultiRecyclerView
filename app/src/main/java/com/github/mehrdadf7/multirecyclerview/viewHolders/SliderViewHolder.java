package com.github.mehrdadf7.multirecyclerview.viewHolders;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.MehrdadApp;
import com.github.mehrdadf7.multirecyclerview.utils.PicassoImageLoadingService;
import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.MySliderAdapter;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;
import ss.com.bannerslider.event.OnSlideClickListener;

public class SliderViewHolder extends RecyclerView.ViewHolder {

  //layout_slider
  private Slider slider;

  public SliderViewHolder(@NonNull View itemView) {
    super(itemView);
    slider = itemView.findViewById(R.id.slider);
  }

  public void bind(ArrayList<ObjectSlider> sliders) {
    Slider.init(new PicassoImageLoadingService(itemView.getContext()));
    slider.setAdapter(new MySliderAdapter(sliders));

    slider.setOnSlideClickListener(position -> Toast.makeText(itemView.getContext(), "item " + position + " clicked", Toast.LENGTH_SHORT).show());

  }

}
