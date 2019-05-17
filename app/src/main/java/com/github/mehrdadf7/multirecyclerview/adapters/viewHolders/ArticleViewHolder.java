package com.github.mehrdadf7.multirecyclerview.adapters.viewHolders;

import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.adapters.child.ArticleMainPageAdapter;
import com.github.mehrdadf7.multirecyclerview.databinding.ViewArticleBinding;
import com.github.mehrdadf7.multirecyclerview.models.News;

import java.util.ArrayList;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

  //view_article
  private ViewArticleBinding binding;
  private ArticleMainPageAdapter articleMainPageAdapter = null;

  public ArticleViewHolder(ViewArticleBinding binding) {
    super(binding.getRoot());
    this. binding = binding;
  }

  public void bind(ArrayList<News.Article> articles) {
    if (articleMainPageAdapter == null) {
      binding.recyclerView.setLayoutAnimation(
          new LayoutAnimationController(
              AnimationUtils.loadAnimation(binding.getRoot().getContext(), android.R.anim.slide_in_left)
          )
      );
      articleMainPageAdapter = new ArticleMainPageAdapter(articles);
      binding.setArticleAdapter(articleMainPageAdapter);
    }
  }

}
