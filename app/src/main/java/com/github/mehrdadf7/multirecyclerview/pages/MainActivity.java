package com.github.mehrdadf7.multirecyclerview.pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.adapters.MultiAdapter;
import com.github.mehrdadf7.multirecyclerview.api.ApiService;
import com.github.mehrdadf7.multirecyclerview.models.News;
import com.github.mehrdadf7.multirecyclerview.models.ObjectBanner;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.utils.InfiniteScrollProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

  private static final String TAG = MainActivity.class.getSimpleName();

  private AppCompatImageView openGithub;
  private RecyclerView recyclerView;
  private MultiAdapter adapter;
  ArrayList<Object> objects = new ArrayList<>();
  private CompositeDisposable disposables = new CompositeDisposable();

  private int page = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViews();
    init();
  }

  private void findViews() {
    recyclerView = findViewById(R.id.recyclerView);
    openGithub = findViewById(R.id.open_github);

    openGithub.setOnClickListener(this);
  }

  private void init() {

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(this, android.R.anim.fade_in)));
    prepareData();
    adapter = new MultiAdapter(objects);
    recyclerView.setAdapter(adapter);

    InfiniteScrollProvider scrollProvider = new InfiniteScrollProvider();
    scrollProvider.attach(recyclerView, () -> {
      disposables.add(
          ApiService.getApiService().getNews(page, "us", "technology")
              .subscribe(news -> {
                adapter.addItems(news.getArticles());
                Toast.makeText(this, "GET", Toast.LENGTH_SHORT).show();
              })
      );

      page += 1;
    });

  }

  private void prepareData() {

    String myImageRes = "android.resource://" + getPackageName() + "/" + R.drawable.mehrdad;
    ArrayList<ObjectSlider> sliders = new ArrayList<>();
    //sliders.add(new ObjectSlider("https://zocada.com/wp-content/uploads/2018/07/android_recycler_view-740x370.png"));
    //sliders.add(new ObjectSlider("https://blog.iamsuleiman.com/wp-content/uploads/2016/05/android-recyclerview-fastadapter-header.png"));
    sliders.add(new ObjectSlider(myImageRes));
    sliders.add(new ObjectSlider(myImageRes));

    //ObjectBanner banner1 = new ObjectBanner("https://advancedrecyclerview.h6ah4i.com/images/ogp.png");
    ObjectBanner banner2 = new ObjectBanner("https://cdn-images-1.medium.com/max/1200/1*mU-dHEyAcrcOz-kHU0YFLQ.png");
    //ObjectBanner banner3 = new ObjectBanner("https://cdn-images-1.medium.com/max/1600/1*yCPgYs8v23tPN3c3VJTwxQ.png");

    Observable<ArrayList<ObjectSlider>> arrayListObservable = Observable.just(sliders)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    Observable<ObjectBanner> objectBannerObservable = Observable.just(banner2)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());

    Observable<ArrayList<Object>> zip = Observable.zip(
        arrayListObservable,
        objectBannerObservable,
        ApiService.getApiService().getNews(page,"us", "technology"),
        ApiService.getApiService().getNews(page,"us", "health"),
        ApiService.getApiService().getNews(page,"us", "business"),
        ApiService.getApiService().getNews(page,"us", "sports"),
        (objectSliders, objectBanner, news, news2, news3, news4) -> {
          ArrayList<Object> objects = new ArrayList<>();
          objects.add(objectSliders);
          objects.add(objectBanner);
          objects.add(news);
          objects.add(news2);
          objects.add(news3);
          objects.add(news4);
          return objects;
        }
    );
    zip.subscribe(new Observer<ArrayList<Object>>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(ArrayList<Object> objectArrayList) {
        for (Object obj : objectArrayList) {
          if (obj instanceof News) {
            objects.add( ((News) obj).getArticles() );
          }
          if (obj instanceof ArrayList && (((ArrayList) obj).get(0) instanceof ObjectSlider)) {
            objects.add(obj);
          }
          if (obj instanceof ObjectBanner) {
            objects.add(obj);
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
      }
    });


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
    if (!disposables.isDisposed()) {
      disposables.dispose();
    }
    super.onStop();
  }
}
