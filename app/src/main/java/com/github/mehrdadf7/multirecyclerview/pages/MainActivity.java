package com.github.mehrdadf7.multirecyclerview.pages;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.MultiAdapter;
import com.github.mehrdadf7.multirecyclerview.databinding.ActivityMainBinding;
import com.github.mehrdadf7.multirecyclerview.models.HeaderModel;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.models.RssNews;
import com.github.mehrdadf7.multirecyclerview.utils.Handlers;
import com.github.mehrdadf7.multirecyclerview.utils.NewsJdomParser;
import com.github.mehrdadf7.multirecyclerview.viewmodels.ArticleListViewModel;
import com.github.mehrdadf7.multirecyclerview.viewmodels.provideData.StateData;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements Observer<StateData<ArrayList<Object>>> {

  private static final String TAG = MainActivity.class.getSimpleName();

  private MultiAdapter multiAdapter = null;
  private ActivityMainBinding binding;
  private Disposable disposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    init();
  }

  private void init() {

    binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    if (multiAdapter == null) {
      multiAdapter = new MultiAdapter();
      binding.setMultiAdapter(multiAdapter);
    }

    Handlers myHandlers = new Handlers(this);
    binding.setHandlers(myHandlers);

    InputStream input = getResources().openRawResource(R.raw.news);
    List<RssNews> rssNews = new NewsJdomParser(input).parseXml();
    for (RssNews rssNew : rssNews) {
      Log.e(TAG, "init: " + rssNew.getTitle());
    }

    ArticleListViewModel viewModel = ViewModelProviders.of(this).get(ArticleListViewModel.class);
    viewModel.getData().observe(this, this);

  }

  @Override
  protected void onStop() {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
      Log.e(TAG, "onStop: " + disposable.isDisposed());
    }
    super.onStop();
  }

  @Override
  public void onChanged(StateData<ArrayList<Object>> stateData) {
    if (stateData.getStatus() == StateData.DataStatus.LOADING) {
      binding.progressBar.setVisibility(View.VISIBLE);
    } else if (stateData.getStatus() == StateData.DataStatus.SUCCESS) {
      Log.e(TAG, "Activity: " + stateData.getData().size());
      for (Object obj : stateData.getData()) {
        if (obj instanceof ArrayList && (((ArrayList) obj).get(0) instanceof ObjectSlider)) {
          binding.getMultiAdapter().addSliders( (ArrayList<ObjectSlider>) obj );
        } else if (obj instanceof News) {
          binding.getMultiAdapter().addArticles( ((News) obj).getArticles() );
        } else if (obj instanceof HeaderModel) {
          binding.getMultiAdapter().addHeader( (HeaderModel) obj );
        }
      }
    } else if (stateData.getStatus() == StateData.DataStatus.ERROR) {
      Log.e(TAG, "ERROR: " + stateData.getError().getMessage());
    } else if (stateData.getStatus() == StateData.DataStatus.DISPOSE) {
      disposable = stateData.getDisposable();
    } else if (stateData.getStatus() == StateData.DataStatus.COMPLETE) {
      binding.getMultiAdapter().notifyDataSetChanged();
      binding.progressBar.setVisibility(View.GONE);
    }
  }




}
