package com.github.mehrdadf7.multirecyclerview.viewHolders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.interfaces.OnHeaderClickListener;
import com.github.mehrdadf7.multirecyclerview.models.NewsHeader;
import com.github.mehrdadf7.multirecyclerview.views.HeaderNews;

public class NewsHeaderViewHolder extends RecyclerView.ViewHolder {

  private OnHeaderClickListener onHeaderClickListener;
  private HeaderNews headerNews;

  public NewsHeaderViewHolder(@NonNull View itemView, OnHeaderClickListener onHeaderClickListener) {
    super(itemView);
    headerNews = itemView.findViewById(R.id.headerNews);
    this.onHeaderClickListener = onHeaderClickListener;
  }

  public void bind(NewsHeader header) {
    headerNews.setTitleCategory(header.getCategory());
    headerNews.setOnParentClickListener(view -> {
      if (onHeaderClickListener != null) {
        onHeaderClickListener.onClick(header);
      }
    });
  }

}
