package com.github.mehrdadf7.multirecyclerview.pages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.child.ArticleAdapter;
import com.github.mehrdadf7.multirecyclerview.databinding.ActivityArticleBinding;
import com.github.mehrdadf7.multirecyclerview.models.HeaderModel;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.utils.InfiniteScrollProvider;
import com.github.mehrdadf7.multirecyclerview.viewmodels.ArticleViewModel;
import com.github.mehrdadf7.multirecyclerview.viewmodels.provideData.StateData;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class NewsActivity extends AppCompatActivity implements Observer<StateData<ArrayList<News.Article>>> {

  private static final String TAG = NewsActivity.class.getSimpleName();

  private ActivityArticleBinding binding;
  private ArticleAdapter adapter = null;
  private ArrayList<News.Article> articles = new ArrayList<>();
  private int page = 1;
  private Disposable disposable;
  private HeaderModel header;
  private ArticleViewModel articleViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_article);
    init();
  }

  private void init() {

    if (getIntent().hasExtra("header")) {
      header = (HeaderModel) getIntent().getSerializableExtra("header");

      articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);

      binding.recyclerView.setLayoutAnimation(
          new LayoutAnimationController(
              AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
          )
      );

      if (adapter == null) {
        adapter = new ArticleAdapter(articles);
      }

      binding.setHeaderModel(header);
      binding.setArticleAdapter(adapter);

      getData();

      InfiniteScrollProvider scrollProvider = new InfiniteScrollProvider();
      scrollProvider.attach(binding.recyclerView, () -> {
        binding.progressBarFooter.setVisibility(View.VISIBLE);
        getData();
      });
    }

  }

  private void getData() {
    articleViewModel.getArticles(page, header.getCategory()).observe(this, this);
  }

  @Override
  public void onChanged(StateData<ArrayList<News.Article>> state) {
    if (state.getStatus() == StateData.DataStatus.LOADING) {
      binding.progressBar.setVisibility(View.VISIBLE);
    } else if (state.getStatus() == StateData.DataStatus.SUCCESS) {
      binding.getArticleAdapter().addArticles(state.getData());
    } else if (state.getStatus() == StateData.DataStatus.ERROR) {
      Log.e(TAG, "ERROR: " + state.getError().getMessage());
    } else if (state.getStatus() == StateData.DataStatus.DISPOSE) {
      disposable = state.getDisposable();
    } else if (state.getStatus() == StateData.DataStatus.COMPLETE) {
      page+=1;
      binding.getArticleAdapter().notifyItemInserted(articles.size() - 1);
      binding.progressBar       .setVisibility(View.GONE);
      binding.progressBarFooter.setVisibility(View.GONE);
      Log.e(TAG, "page: " + page);
    }
  }

  @Override
  protected void onStop() {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }
    super.onStop();
  }

}
