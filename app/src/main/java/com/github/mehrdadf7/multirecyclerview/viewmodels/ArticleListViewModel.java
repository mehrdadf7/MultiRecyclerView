package com.github.mehrdadf7.multirecyclerview.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.mehrdadf7.multirecyclerview.viewmodels.provideData.StateLiveData;

import java.util.ArrayList;

public class ArticleListViewModel extends AndroidViewModel {

  private StateLiveData<ArrayList<Object>> data = new StateLiveData<>();
  private ArticleListRepo repo;

  public ArticleListViewModel(@NonNull Application application) {
    super(application);
    repo = ArticleListRepo.getInstance();
    init();
  }

  private void init() {
    this.data = repo.getData();
  }

  public void getArticles() {
    this.data = repo.getData();
  }

  public StateLiveData<ArrayList<Object>> getData() {
    return data;
  }
}
