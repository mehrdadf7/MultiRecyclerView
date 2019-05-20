package com.github.mehrdadf7.multirecyclerview.viewmodels.provideData;

import androidx.lifecycle.LiveData;

import io.reactivex.disposables.Disposable;

public class StateLiveData<T> extends LiveData<StateData<T>> {

  public void setDispose(Disposable disposable) {
    setValue(new StateData<T>().dispose(disposable));
  }

  public void setLoading() {
    setValue(new StateData<T>().loading());
  }

  public void setError(Throwable throwable) {
    setValue(new StateData<T>().error(throwable));
  }

  public void setSuccess(T data) {
    setValue(new StateData<T>().success(data));
  }

  public void setComplete() {
    setValue(new StateData<T>().complete());
  }

}
