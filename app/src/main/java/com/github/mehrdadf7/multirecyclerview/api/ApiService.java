package com.github.mehrdadf7.multirecyclerview.api;

import com.github.mehrdadf7.multirecyclerview.models.News;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiService {

  private static ApiService apiService = null;

  private ApiService() {
  }

  public Observable<News> getNews(int page, String country, String category) {
    return RetrofitClass.getApiService()
        .getNews(page, country, category, "847968758fc443dcbef779b238029441")
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
