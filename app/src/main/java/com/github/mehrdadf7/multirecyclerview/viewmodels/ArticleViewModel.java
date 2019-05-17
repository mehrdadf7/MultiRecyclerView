package com.github.mehrdadf7.multirecyclerview.viewmodels;

import androidx.lifecycle.ViewModel;

import com.github.mehrdadf7.multirecyclerview.api.ApiService;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.utils.Constants;
import com.github.mehrdadf7.multirecyclerview.viewmodels.provideData.StateLiveData;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ArticleViewModel extends ViewModel {

  public StateLiveData<ArrayList<News.Article>> getArticles(int page, String category) {
    StateLiveData<ArrayList<News.Article>> articles = new StateLiveData<>();
    articles.setLoading();
    ApiService.getApiService().getNews(page, Constants.PAGE_SIZE, "us", category)
        .subscribe(new Observer<News>() {
          @Override
          public void onSubscribe(Disposable d) {
            articles.setDispose(d);
          }

          @Override
          public void onNext(News news) {
            articles.setSuccess(news.getArticles());
          }

          @Override
          public void onError(Throwable e) {
            articles.setError(e);
          }

          @Override
          public void onComplete() {
            articles.setComplete();
          }
        });
    return articles;
  }

}
