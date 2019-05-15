package com.github.mehrdadf7.multirecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.models.ObjectBanner;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.viewHolders.BannerViewHolder;
import com.github.mehrdadf7.multirecyclerview.viewHolders.NewsViewHolder;
import com.github.mehrdadf7.multirecyclerview.viewHolders.SliderViewHolder;

import java.util.ArrayList;

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private ArrayList<Object> objects;

  private static final int VIEW_SLIDER = 1;
  private static final int VIEW_NEWS = 2;
  private static final int VIEW_BANNER = 3;

  public MultiAdapter(ArrayList<Object> objects) {
    this.objects = objects;
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    if (viewType == VIEW_SLIDER) {
      return new SliderViewHolder(layoutInflater.inflate(R.layout.layout_slider, parent, false));
    } else if (viewType == VIEW_BANNER) {
      return new BannerViewHolder(layoutInflater.inflate(R.layout.layout_banner, parent, false));
    } else if (viewType == VIEW_NEWS) {
      return new NewsViewHolder(layoutInflater.inflate(R.layout.layout_news, parent, false));
    }
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    if (getItemViewType(position) == VIEW_SLIDER) {
      ArrayList<ObjectSlider> sliders = (ArrayList<ObjectSlider>) objects.get(position);
      ((SliderViewHolder) holder).bind(sliders);
    } else if (getItemViewType(position) == VIEW_NEWS) {
      ArrayList<News.Article> articles = (ArrayList<News.Article>) objects.get(position);
      ((NewsViewHolder) holder).bind(articles);
    } else if (getItemViewType(position) == VIEW_BANNER) {
      ObjectBanner banner = (ObjectBanner) objects.get(position);
      ((BannerViewHolder) holder).bind(banner);
    }
  }

  public void addItems(ArrayList<News.Article> articles) {
    objects.add(articles);
    notifyItemInserted(objects.size() - 1);
  }

  @Override
  public int getItemViewType(int position) {
    Object obj = objects.get(position);
    if (obj instanceof ArrayList) {
      if (((ArrayList<?>) obj).get(position) instanceof ObjectSlider)
        return VIEW_SLIDER;
      else if (((ArrayList<?>) obj).get(position) instanceof News.Article)
        return VIEW_NEWS;
    } else {
      if (objects.get(position) instanceof ObjectBanner)
        return VIEW_BANNER;
    }
    return -1;
  }

  @Override
  public int getItemCount() {
    return objects.size();
  }

}
