package com.github.mehrdadf7.multirecyclerview;

import android.app.Application;
import android.content.Context;

public class MehrdadApp extends Application {

  private static Context context;

  public static Context getContext() {
    return context;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    context = this;
  }
}
