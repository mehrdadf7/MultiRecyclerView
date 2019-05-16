package com.github.mehrdadf7.multirecyclerview.pages;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.NewsAdapter;
import com.github.mehrdadf7.multirecyclerview.adapters.NewsMainPageAdapter;
import com.github.mehrdadf7.multirecyclerview.api.ApiService;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.models.NewsHeader;
import com.github.mehrdadf7.multirecyclerview.utils.Constants;
import com.github.mehrdadf7.multirecyclerview.utils.InfiniteScrollProvider;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NewsActivity extends BaseActivity implements Observer<News> {

  private static final String TAG = NewsActivity.class.getSimpleName();

  private AppCompatImageView back;
  private AppCompatTextView title;
  private RecyclerView recyclerView;
  private NewsAdapter adapter;
  private ProgressBar progressBar, progressBar_footer;
  private ArrayList<News.Article> articles = new ArrayList<>();
  private int page = 1;
  private Disposable disposable;
  private NewsHeader header;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    findViews();
    init();
  }

  @Override
  protected int getLayout() {
    return R.layout.activity_news;
  }

  private void findViews() {
    back               = findViewById(R.id.back);
    title              = findViewById(R.id.title);
    recyclerView       = findViewById(R.id.recyclerView);
    progressBar        = findViewById(R.id.progressBar);
    progressBar_footer = findViewById(R.id.progressBar_footer);
  }

  private void init() {

    if (getIntent().hasExtra("header")) {
      header = (NewsHeader) getIntent().getSerializableExtra("header");
      title.setText(header.getCategory());

      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)));
      getData();
      adapter = new NewsAdapter(articles);
      recyclerView.setAdapter(adapter);

      InfiniteScrollProvider scrollProvider = new InfiniteScrollProvider();
      scrollProvider.attach(recyclerView, () -> {
        progressBar_footer.setVisibility(View.VISIBLE);
        getData();
      });
    }

    back.setOnClickListener(view -> finish());

  }

  private void getData() {
    ApiService.getApiService().getNews(page, Constants.PAGE_SIZE, "us", header.getCategory()).subscribe(this);
  }

  @Override
  public void onSubscribe(Disposable d) {
    disposable = d;
  }

  @Override
  public void onNext(News news) {
    adapter.addArticles(news.getArticles());
  }

  @Override
  public void onError(Throwable e) {
    Log.e(TAG, "onError: " + e.getMessage());
  }

  @Override
  public void onComplete() {
    page+=1;
    adapter.notifyItemInserted(articles.size() - 1);
    progressBar       .setVisibility(View.GONE);
    progressBar_footer.setVisibility(View.GONE);
  }

  @Override
  protected void onStop() {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }
    super.onStop();
  }
}
