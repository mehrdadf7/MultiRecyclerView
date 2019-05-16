package com.github.mehrdadf7.multirecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.interfaces.OnHeaderClickListener;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.models.NewsHeader;
import com.github.mehrdadf7.multirecyclerview.models.ObjectBanner;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.viewHolders.BannerViewHolder;
import com.github.mehrdadf7.multirecyclerview.viewHolders.NewsHeaderViewHolder;
import com.github.mehrdadf7.multirecyclerview.viewHolders.NewsViewHolder;
import com.github.mehrdadf7.multirecyclerview.viewHolders.SliderViewHolder;

import java.util.ArrayList;

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private ArrayList<Object> objects = new ArrayList<>();

  private static final int VIEW_SLIDER = 1;
  private static final int VIEW_NEWS   = 2;
  private static final int VIEW_HEADER_NEWS = 3;
  private static final int VIEW_BANNER = 4;
  private OnHeaderClickListener onHeaderClickListener;

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    switch (viewType) {
      case VIEW_SLIDER:
        return new SliderViewHolder(layoutInflater.inflate(R.layout.layout_slider, parent, false));
      case VIEW_BANNER:
        return new BannerViewHolder(layoutInflater.inflate(R.layout.layout_banner, parent, false));
      case VIEW_HEADER_NEWS:
        return new NewsHeaderViewHolder(layoutInflater.inflate(R.layout.layout_header, parent, false),
            onHeaderClickListener);
      case VIEW_NEWS:
        return new NewsViewHolder(layoutInflater.inflate(R.layout.layout_news, parent, false));
        default: return null;
    }
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case VIEW_SLIDER:
        ArrayList<ObjectSlider> sliders = (ArrayList<ObjectSlider>) objects.get(position);
        ((SliderViewHolder) holder).bind(sliders);
        break;
      case VIEW_NEWS:
        ArrayList<News.Article> articles = (ArrayList<News.Article>) objects.get(position);
        ((NewsViewHolder) holder).bind(articles);
        break;
      case VIEW_HEADER_NEWS:
        NewsHeader headerNews = (NewsHeader) objects.get(position);
        ((NewsHeaderViewHolder) holder).bind(headerNews);
        break;
      case VIEW_BANNER:
        ObjectBanner banner = (ObjectBanner) objects.get(position);
        ((BannerViewHolder) holder).bind(banner);
        break;
    }
  }

  public void clear() {
    objects.clear();
  }

  public void addArticles(ArrayList<News.Article> articles) {
    objects.add(articles);
  }

  public void addHeader(NewsHeader header) {
    objects.add(header);
  }

  public void addSliders(ArrayList<ObjectSlider> sliders) {
    objects.add(sliders);
  }

  public void addBanner(ObjectBanner banner) {
    objects.add(banner);
  }

  @Override
  public int getItemViewType(int position) {
    Object obj = objects.get(position);
    if (obj instanceof ArrayList) {
      if (((ArrayList) obj).get(position) instanceof ObjectSlider)
        return VIEW_SLIDER;
      else if (((ArrayList) obj).get(position) instanceof News.Article)
        return VIEW_NEWS;
    } else {
      if (objects.get(position) instanceof ObjectBanner)
        return VIEW_BANNER;
      if (objects.get(position) instanceof NewsHeader)
        return VIEW_HEADER_NEWS;
    }
    return -1;
  }

  @Override
  public int getItemCount() {
    return objects.size();
  }

  public void setOnHeaderClickListener(OnHeaderClickListener onHeaderClickListener) {
    this.onHeaderClickListener = onHeaderClickListener;
  }
}
