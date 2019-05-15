package com.github.mehrdadf7.multirecyclerview.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

  private ArrayList<News.Article> articles;

  public NewsAdapter(ArrayList<News.Article> articles) {
    this.articles = articles;
  }

  @NonNull
  @Override
  public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new NewsViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.layout_news_row, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
    holder.bind(articles.get(position));
  }

  @Override
  public int getItemCount() {
    return 10;
  }

  public class NewsViewHolder extends RecyclerView.ViewHolder {

    private AppCompatTextView tv_count, textView;

    public NewsViewHolder(@NonNull View itemView) {
      super(itemView);
      tv_count = itemView.findViewById(R.id.tv_count);
      textView = itemView.findViewById(R.id.tv);
    }

    public void bind(News.Article article) {
      tv_count.setText( (getAdapterPosition() + 1) + ". ");

      textView.setText(article.getPublishedAt());

      itemView.setOnClickListener(view ->
          Toast.makeText(itemView.getContext(), article.getTitle(), Toast.LENGTH_SHORT).show()
      );

    }

  }
}
