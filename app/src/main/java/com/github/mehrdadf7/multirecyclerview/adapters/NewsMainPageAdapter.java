package com.github.mehrdadf7.multirecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.MehrdadApp;
import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class NewsMainPageAdapter extends RecyclerView.Adapter<NewsMainPageAdapter.NewsViewHolder> {

  private ArrayList<News.Article> articles;

  public NewsMainPageAdapter(ArrayList<News.Article> articles) {
    this.articles = articles;
  }

  @NonNull
  @Override
  public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new NewsViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_news_small_row, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
    holder.bind(articles.get(position));
  }

  public void addArticles(ArrayList<News.Article> articles) {
    this.articles.addAll(articles);
  }

  @Override
  public int getItemCount() {
    return articles.size();
  }

  public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView title, description;

    public NewsViewHolder(@NonNull View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.image);
      title = itemView.findViewById(R.id.title);
      description = itemView.findViewById(R.id.description);
    }

    public void bind(News.Article article) {

      if (article.getTitle() != null) {
        title.setText( article.getTitle());
      } else {
        title.setText( article.getContent());
      }
      description.setText(article.getDescription());

      ImageLoader imageLoader = ImageLoader.getInstance();
      imageLoader.init(MehrdadApp.config);
      imageLoader.displayImage(article.getUrlToImage(), imageView, MehrdadApp.options);

    }

  }
}
