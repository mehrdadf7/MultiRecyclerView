package com.github.mehrdadf7.multirecyclerview.api;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {

  private static RetrofitClass retrofitClass = null;

  private RetrofitClass() {
  }

  private HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
      .setLevel(HttpLoggingInterceptor.Level.BODY);

  private OkHttpClient client = new OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .readTimeout(60, TimeUnit.SECONDS)
      .connectTimeout(60, TimeUnit.SECONDS)
      .build();

  private OkHttpClient getUnsafeOkHttpClient() {
    try {
      final TrustManager[] trustAllCerts = new TrustManager[]{
          new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return new java.security.cert.X509Certificate[]{};
            }
          }
      };

      final SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

      final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

      OkHttpClient.Builder builder = new OkHttpClient.Builder()
          .addInterceptor(loggingInterceptor)
          .readTimeout(60, TimeUnit.SECONDS)
          .connectTimeout(60, TimeUnit.SECONDS);
      builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
      builder.hostnameVerifier((hostname, session) -> true);
      return builder.build();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Retrofit retrofit = new Retrofit.Builder()
      .baseUrl("https://newsapi.org/v2/")
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .client(getUnsafeOkHttpClient())
      .build();

  private static RetrofitClass getRetrofitClass() {
    if (retrofitClass == null) {
      retrofitClass = new RetrofitClass();
    }
    return retrofitClass;
  }

  static Services getApiService() {
    return getRetrofitClass().retrofit.create(Services.class);
  }

}
