package com.github.mehrdadf7.multirecyclerview;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.github.mehrdadf7.multirecyclerview.interfaces.ArticleDao;
import com.github.mehrdadf7.multirecyclerview.models.ArticleDb;

@Database(entities = {ArticleDb.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
  public abstract ArticleDao articleDao();
}
