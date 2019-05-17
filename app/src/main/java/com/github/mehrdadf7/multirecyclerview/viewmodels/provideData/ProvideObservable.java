package com.github.mehrdadf7.multirecyclerview.viewmodels.provideData;

import com.github.mehrdadf7.multirecyclerview.models.HeaderModel;
import com.github.mehrdadf7.multirecyclerview.models.ObjectSlider;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProvideObservable {

  public static Observable<HeaderModel> provideHeader(HeaderModel header) {
    return Observable.just(header)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public static Observable<ArrayList<ObjectSlider>> provideSlider(ArrayList<ObjectSlider> sliders) {
    return Observable.just(sliders)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread());
  }

}
