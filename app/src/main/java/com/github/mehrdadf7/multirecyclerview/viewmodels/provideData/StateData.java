package com.github.mehrdadf7.multirecyclerview.viewmodels.provideData;

import io.reactivex.disposables.Disposable;

public class StateData<T> {

  private DataStatus status;

  private Disposable disposable;
  private T data;
  private Throwable error;

  public StateData<T> dispose(Disposable disposable) {
    this.status = DataStatus.DISPOSE;
    //this.data = null;
    //this.error = null;
    this.disposable = disposable;
    return this;
  }

  public StateData<T> loading() {
    this.status = DataStatus.LOADING;
    this.data = null;
    this.error = null;
    return this;
  }

  public StateData<T> success(T data) {
    this.status = DataStatus.SUCCESS;
    this.data = data;
    this.error = null;
    return this;
  }

  public StateData<T> error(Throwable error) {
    this.status = DataStatus.ERROR;
    this.data = null;
    this.error = error;
    return this;
  }

  public StateData<T> complete() {
    this.status = DataStatus.COMPLETE;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public T getData() {
    return data;
  }

  public Throwable getError() {
    return error;
  }

  public Disposable getDisposable() {
    return disposable;
  }

  public enum DataStatus {
    SUCCESS,
    ERROR,
    LOADING,
    COMPLETE,
    DISPOSE
  }

}
