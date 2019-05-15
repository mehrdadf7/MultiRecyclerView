package com.github.mehrdadf7.multirecyclerview.viewHolders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.views.NewsList;

import java.util.ArrayList;
import java.util.List;

public class NewsViewHolder extends RecyclerView.ViewHolder {

  //layout_news
  private NewsList newsList;

  public NewsViewHolder(@NonNull View itemView) {
    super(itemView);
    newsList = itemView.findViewById(R.id.newsList);
  }

  public void bind(ArrayList<News.Article> articles) {
    newsList.attachList(articles);
  }
}
