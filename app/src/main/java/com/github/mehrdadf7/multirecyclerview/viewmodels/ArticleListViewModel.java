package com.github.mehrdadf7.multirecyclerview.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.mehrdadf7.multirecyclerview.R;
import com.github.mehrdadf7.multirecyclerview.api.ApiService;
import com.github.mehrdadf7.multirecyclerview.models.HeaderModel;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;
import com.github.mehrdadf7.multirecyclerview.utils.Constants;
import com.github.mehrdadf7.multirecyclerview.viewmodels.provideData.ProvideObservable;
import com.github.mehrdadf7.multirecyclerview.viewmodels.provideData.StateLiveData;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ArticleListViewModel extends AndroidViewModel {

  public ArticleListViewModel(@NonNull Application application) {
    super(application);
  }

  public StateLiveData<ArrayList<Object>> getData() {
    StateLiveData<ArrayList<Object>> data = new StateLiveData<>();

    data.setLoading();

    HeaderModel technology = new HeaderModel(Constants.HEADER_TECHNOLOGY);
    HeaderModel health     = new HeaderModel(Constants.HEADER_HEALTH);
    HeaderModel business   = new HeaderModel(Constants.HEADER_BUSINESS);
    HeaderModel sports     = new HeaderModel(Constants.HEADER_SPORTS);

    Observable.zip(
        ProvideObservable.provideSlider(provideSlider()),
        ProvideObservable.provideHeader(technology),
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_TECHNOLOGY),
        ProvideObservable.provideHeader(health),
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_HEALTH),
        ProvideObservable.provideHeader(business),
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_BUSINESS),
        ProvideObservable.provideHeader(sports),
        ApiService.getApiService().getNews(Constants.PAGE_SIZE, Constants.LANGUAGE, Constants.QUERY_PARAMETER_SPORTS),
        (objectSliders    ,
         technology_object,
         news_technology  ,
         health_object    ,
         news_health      ,
         business_object  ,
         news_business    ,
         sports_object    ,
         news_sports) -> {
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
    ).subscribe(new Observer<ArrayList<Object>>() {
      @Override
      public void onSubscribe(Disposable disposable) {
        data.setDispose(disposable);
      }

      @Override
      public void onNext(ArrayList<Object> objectArrayList) {
        data.setSuccess(objectArrayList);
      }

      @Override
      public void onError(Throwable e) {
        data.setError(e);
      }

      @Override
      public void onComplete() {
        data.setComplete();
      }
    });
    return data;
  }

  private ArrayList<ObjectSlider> provideSlider() {
    String myImageRes = "android.resource://" + getApplication().getPackageName() + "/" + R.drawable.mehrdad;
    ArrayList<ObjectSlider> sliders = new ArrayList<>();
    sliders.add(new ObjectSlider(myImageRes));
    sliders.add(new ObjectSlider(myImageRes));
    return sliders;
  }


}
