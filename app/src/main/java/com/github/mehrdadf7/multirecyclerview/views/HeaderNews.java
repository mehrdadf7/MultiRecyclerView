package com.github.mehrdadf7.multirecyclerview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mehrdadf7.multirecyclerview.R;

public class HeaderNews extends RelativeLayout {

  private RelativeLayout parent;
  private TextView titleCategory;

  public HeaderNews(Context context) {
    super(context);
    init();
  }

  public HeaderNews(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public HeaderNews(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    View rootView = LayoutInflater.from(getContext())
        .inflate(R.layout.view_news_header, this, true);
    parent = rootView.findViewById(R.id.parentTitle);
    titleCategory = rootView.findViewById(R.id.titleCategory);
  }

  public void setTitleCategory(String category) {
    this.titleCategory.setText(category);
  }

  public void setOnParentClickListener(OnClickListener onClickListener) {
    this.parent.setOnClickListener(onClickListener);
  }

}
