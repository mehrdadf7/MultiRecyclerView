package com.github.mehrdadf7.multirecyclerview.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.github.mehrdadf7.multirecyclerview.models.HeaderModel;
import com.github.mehrdadf7.multirecyclerview.pages.NewsActivity;

public class Handlers {

  private Context context;

  public Handlers(Context context) {
    this.context = context;
  }

  public void onArticleHeaderClick(HeaderModel headerModel) {
    Intent intent = new Intent(context, NewsActivity.class);
    intent.putExtra("header" , headerModel);
    context.startActivity(intent);
  }

  public void openLinkGithub() {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse("http://github.com/mehrdadf7/MultiRecyclerView"));
    context.startActivity(intent);
  }

}
