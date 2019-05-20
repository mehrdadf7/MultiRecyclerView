package com.github.mehrdadf7.multirecyclerview.utils;

import com.github.mehrdadf7.multirecyclerview.models.RssNews;

import org.jdom2.Element;

import java.io.InputStream;

public class NewsJdomParser extends XmlJdomParser<RssNews> {

  private InputStream inputStream;

  public NewsJdomParser(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  @Override
  public InputStream getInput() {
    return inputStream;
  }

  @Override
  public String getObjectsNodeKey() {
    return "channel";
  }

  @Override
  public String getObjectNodesKey() {
    return "item";
  }

  @Override
  public RssNews getObjectFromNode(Element node) {
    RssNews rssNews = new RssNews();
    rssNews.setTitle      (node.getChildText("title"));
    rssNews.setGuid       (node.getChildText("guid"));
    rssNews.setPubDate    (node.getChildText("pubDate"));
    rssNews.setCategory   (node.getChildText("category"));
    rssNews.setDescription(node.getChildText("description"));
    rssNews.setLink       (node.getChildText("link"));
    return rssNews;
  }

}
