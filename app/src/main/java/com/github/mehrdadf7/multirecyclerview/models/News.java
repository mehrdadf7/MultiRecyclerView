package com.github.mehrdadf7.multirecyclerview.models;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.github.mehrdadf7.multirecyclerview.component.ImageLoadingInjector;

import java.util.ArrayList;

public class News {

  private ArrayList<Article> articles;

  public ArrayList<Article> getArticles() {
    return articles;
  }

  public static class Article extends BaseObservable {
    private String author, title, description, url, urlToImage, publishedAt, content;

    public Article(String title, String description) {
      this.title = title;
      this.description = description;
    }

    @BindingAdapter({"imageArticle"})
    public static void setImageArticle(ImageView imageView , String url) {
      ImageLoadingInjector.getImageLoading().loadImage(url, imageView);
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }


    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }


    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }


    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }


    public String getUrlToImage() {
      return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
      this.urlToImage = urlToImage;
    }


    public String getPublishedAt() {
      return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
      this.publishedAt = publishedAt;
    }


    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }

}
