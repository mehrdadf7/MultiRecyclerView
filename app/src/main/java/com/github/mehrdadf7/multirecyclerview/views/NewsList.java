package com.github.mehrdadf7.multirecyclerview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.NewsMainPageAdapter;
import com.github.mehrdadf7.multirecyclerview.models.News;

import java.util.ArrayList;

public class NewsList extends LinearLayoutCompat {

  private RecyclerView recyclerView;

  public NewsList(Context context) {
    super(context);
    init();
  }

  public NewsList(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public NewsList(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    View rootView = LayoutInflater.from(getContext())
        .inflate(R.layout.view_news_list, this, true);
    recyclerView = rootView.findViewById(R.id.recyclerView);
  }

  public void attachList(ArrayList<News.Article> articles) {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, true));
    recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in)));
    NewsMainPageAdapter newsMainPageAdapter = new NewsMainPageAdapter(articles);
    recyclerView.setAdapter(newsMainPageAdapter);
  }

}
