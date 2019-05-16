package com.github.mehrdadf7.multirecyclerview.api;

import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiService {

  private static ApiService apiService = null;

  private ApiService() {
  }

  public Observable<News> getNews(int pageSize, String country, String category) {
    return getNews(null, pageSize, country, category);
  }

  public Observable<News> getNews(Integer page, int pageSize, String country, String category) {
    return RetrofitClass.getApiService()
        .getNews(page, pageSize, country, category, Constants.API_KEY)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public Observable<News> getArticles(int pageSize, int page) {
    return RetrofitClass.getApiService()
        .getArticles(pageSize, page, "wsj.com,nytimes.com", Constants.API_KEY)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static ApiService getApiService() {
    if (apiService == null) {
      apiService = new ApiService();
    }
    return apiService;
  }
}
