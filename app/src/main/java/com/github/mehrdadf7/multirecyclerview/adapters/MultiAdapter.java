package com.github.mehrdadf7.multirecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.viewHolders.ArticleViewHolder;
import com.github.mehrdadf7.multirecyclerview.adapters.viewHolders.HeaderViewHolder;
import com.github.mehrdadf7.multirecyclerview.adapters.viewHolders.SliderViewHolder;
import com.github.mehrdadf7.multirecyclerview.databinding.ViewArticleBinding;
import com.github.mehrdadf7.multirecyclerview.databinding.ViewHeaderBinding;
import com.github.mehrdadf7.multirecyclerview.databinding.ViewSliderBinding;
import com.github.mehrdadf7.multirecyclerview.models.HeaderModel;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;

import java.util.ArrayList;

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private ArrayList<Object> objects = new ArrayList<>();

  private static final int VIEW_SLIDER  = 1;
  private static final int VIEW_ARTICLE = 2;
  private static final int VIEW_HEADER  = 3;

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    ViewDataBinding binding;
    switch (viewType) {
      case VIEW_SLIDER:
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.view_slider, parent, false);
        return new SliderViewHolder( (ViewSliderBinding) binding);
      case VIEW_HEADER: {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.view_header, parent, false);
        return new HeaderViewHolder( (ViewHeaderBinding) binding);
      }
      case VIEW_ARTICLE:
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.view_article, parent, false);
        return new ArticleViewHolder( (ViewArticleBinding) binding);
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
      case VIEW_ARTICLE:
        ArrayList<News.Article> articles = (ArrayList<News.Article>) objects.get(position);
        ((ArticleViewHolder) holder).bind(articles);
        break;
      case VIEW_HEADER:
        HeaderModel headerModel = (HeaderModel) objects.get(position);
        ((HeaderViewHolder) holder).bind(headerModel);
        break;
    }
  }

  public void addArticles(ArrayList<News.Article> articles) {
    objects.add(articles);
  }

  public void addHeader(HeaderModel header) {
    objects.add(header);
  }

  public void addSliders(ArrayList<ObjectSlider> sliders) {
    objects.add(sliders);
  }

  @Override
  public int getItemViewType(int position) {
    Object obj = objects.get(position);
    if (obj instanceof ArrayList) {
      if (((ArrayList) obj).get(position) instanceof ObjectSlider)
        return VIEW_SLIDER;
      else if (((ArrayList) obj).get(position) instanceof News.Article)
        return VIEW_ARTICLE;
    } else {
      if (objects.get(position) instanceof HeaderModel)
        return VIEW_HEADER;
    }
    return -1;
  }

  @Override
  public int getItemCount() {
    return objects.size();
  }

}
