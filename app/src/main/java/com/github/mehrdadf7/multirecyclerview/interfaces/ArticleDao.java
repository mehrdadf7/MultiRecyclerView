package com.github.mehrdadf7.multirecyclerview.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.github.mehrdadf7.multirecyclerview.models.ArticleDb;

import java.util.List;

@Dao
public interface ArticleDao {

  @Query("SELECT * FROM ArticleDb")
  LiveData<List<ArticleDb>> getArticles();

}
