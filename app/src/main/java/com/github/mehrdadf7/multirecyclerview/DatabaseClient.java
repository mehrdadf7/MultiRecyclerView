package com.github.mehrdadf7.multirecyclerview;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {

  private Context context;
  private static DatabaseClient instance = null;
  private AppDatabase appDatabase;

  public DatabaseClient(Context context) {
    this.context = context;
    appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Articles").build();
  }

  public AppDatabase getAppDatabase() {
    return appDatabase;
  }

  public static synchronized DatabaseClient getInstance(Context context) {
    if (instance == null) {
      instance = new DatabaseClient(context);
    }
    return instance;
  }
}
