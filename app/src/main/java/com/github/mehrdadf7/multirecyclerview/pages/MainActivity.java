package com.github.mehrdadf7.multirecyclerview.pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.MultiAdapter;
import com.github.mehrdadf7.multirecyclerview.api.ApiService;
import com.github.mehrdadf7.multirecyclerview.interfaces.OnHeaderClickListener;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.models.NewsHeader;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.utils.Constants;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity
    implements View.OnClickListener, Observer<ArrayList<Object>> {

  private static final String TAG = MainActivity.class.getSimpleName();

  private AppCompatImageView openGithub;
  private RecyclerView recyclerView;
  private MultiAdapter adapter;
  private ProgressBar progressBar;
  private Disposable disposable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    findViews();
    init();
  }

  @Override
  protected int getLayout() {
    return R.layout.activity_main;
  }

  private void findViews() {
    openGithub = findViewById(R.id.open_github);
    recyclerView = findViewById(R.id.recyclerView);
    progressBar = findViewById(R.id.progressBar);

    openGithub.setOnClickListener(this);
  }

  private void init() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setLayoutAnimation(
        new LayoutAnimationController(
            AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left)));
    adapter = new MultiAdapter();
    recyclerView.setAdapter(adapter);
    prepareData();

    adapter.setOnHeaderClickListener(header -> {
      Intent intent = new Intent(MainActivity.this, NewsActivity.class);
      intent.putExtra("header" , header);
      startActivity(intent);
    });

  }

  private void prepareData() {

    String myImageRes = "android.resource://" + getPackageName() + "/" + R.drawable.mehrdad;
    ArrayList<ObjectSlider> sliders = new ArrayList<>();
    sliders.add(new ObjectSlider(myImageRes));
    sliders.add(new ObjectSlider(myImageRes));

    NewsHeader technology = new NewsHeader(Constants.HEADER_TECHNOLOGY);
    NewsHeader health     = new NewsHeader(Constants.HEADER_HEALTH);
    NewsHeader business   = new NewsHeader(Constants.HEADER_BUSINESS);
    NewsHeader sports     = new NewsHeader(Constants.HEADER_SPORTS);

    Observable<ArrayList<ObjectSlider>> observable_sliders = Observable.just(sliders)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    Observable<NewsHeader> observable_technology = Observable.just(technology)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    Observable<NewsHeader> observable_health = Observable.just(health)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    Observable<NewsHeader> observable_business = Observable.just(business)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    Observable<NewsHeader> observable_sports = Observable.just(sports)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    Observable.zip(
        observable_sliders,
        observable_technology,
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_TECHNOLOGY),
        observable_health,
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_HEALTH),
        observable_business,
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_BUSINESS),
        observable_sports,
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_SPORTS),
        (objectSliders    ,
         technology_object, news_technology,
         health_object    , news_health,
         business_object  , news_business,
         sports_object    , news_sports) -> {
          ArrayList<Object> objects = new ArrayList<>();
          objects.add(objectSliders);
          objects.add(technology_object);
          objects.add(news_technology);
          objects.add(health_object);
          objects.add(news_health);
          objects.add(business_object);
          objects.add(news_business);
          objects.add(sports_object);
          objects.add(news_sports);
          return objects;
        }
    ).subscribe(this);


  }

  @Override
  public void onSubscribe(Disposable d) {
    disposable = d;
  }

  @Override
  public void onNext(ArrayList<Object> objectArrayList) {
    for (Object obj : objectArrayList) {
      if (obj instanceof ArrayList && (((ArrayList) obj).get(0) instanceof ObjectSlider)) {
        adapter.addSliders( (ArrayList<ObjectSlider>) obj );
      } else if (obj instanceof News) {
        adapter.addArticles( ((News) obj).getArticles() );
      } else if (obj instanceof NewsHeader) {
        adapter.addHeader( (NewsHeader) obj );
      }
    }
  }

  @Override
  public void onError(Throwable e) {
    Log.e(TAG, "onError: " + e.getMessage());
  }

  @Override
  public void onComplete() {
    adapter.notifyDataSetChanged();
    progressBar.setVisibility(View.GONE);
  }

  @Override
  public void onClick(View v) {
    if (v == openGithub) {
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(Uri.parse("http://github.com/mehrdadf7"));
      Intent.createChooser(intent, "Select Browser");
    }
  }

  @Override
  protected void onStop() {
    if (!disposable.isDisposed()) {
      disposable.dispose();
    }
    super.onStop();
  }

}
