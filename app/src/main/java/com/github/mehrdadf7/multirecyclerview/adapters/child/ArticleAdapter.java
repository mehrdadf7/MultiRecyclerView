package com.github.mehrdadf7.multirecyclerview.adapters.child;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.databinding.LayoutArticleVerticalRowBinding;
import com.github.mehrdadf7.multirecyclerview.models.News;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.NewsViewHolder> {

  private ArrayList<News.Article> articles;
  private LayoutInflater layoutInflater;

  public ArticleAdapter(ArrayList<News.Article> articles) {
    this.articles = articles;
  }

  @NonNull
  @Override
  public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    if(layoutInflater == null) {
      layoutInflater = LayoutInflater.from(parent.getContext());
    }
    LayoutArticleVerticalRowBinding binding = DataBindingUtil
        .inflate(layoutInflater,
            R.layout.layout_article_vertical_row, parent, false);
    return new NewsViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
    holder.binding.setArticle(articles.get(position));
  }

  public void addArticles(ArrayList<News.Article> articles) {
    this.articles.addAll(articles);
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }

  public class NewsViewHolder extends RecyclerView.ViewHolder {

    private LayoutArticleVerticalRowBinding binding;

    public NewsViewHolder(LayoutArticleVerticalRowBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
