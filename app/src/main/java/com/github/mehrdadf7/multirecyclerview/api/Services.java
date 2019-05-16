package com.github.mehrdadf7.multirecyclerview.api;

import com.github.mehrdadf7.multirecyclerview.models.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {

  @GET("top-headlines")
  Observable<News> getNews(
      @Query("page") Integer page,
      @Query("pageSize") Integer pageSize,
      @Query("country") String country,
      @Query("category") String category,
      @Query("apiKey") String apiKey
  );

  @GET("everything")
  Observable<News> getArticles(
      @Query("pageSize") Integer pageSize,
      @Query("page") Integer page,
      @Query("domains") String domains,
      @Query("apiKey") String apiKey
  );

}
