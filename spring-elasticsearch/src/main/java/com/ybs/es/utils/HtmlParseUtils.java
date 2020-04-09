package com.ybs.es.utils;

import com.ybs.es.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * HtmlParseUtils
 *
 * @author Paulson
 * @date 2020/4/9 22:41
 */
public class HtmlParseUtils {
    public static void main(String[] args) throws IOException {
        new HtmlParseUtils().parseJD("心理学").forEach(System.out::println);
    }

    public List<Content> parseJD(String keywords) throws IOException {
        // 获取请求 https://search.jd.com/Search?keyword=java
        // 前提，需要联网！ 不能获取ajax请求
        String url = "https://search.jd.com/Search?keyword=" + keywords;

        // 解析网页。（返回D哦粗门头就是浏览器的Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        // 所有在js中的元素可以在这里获取
        Element element = document.getElementById("J_goodsList");
        // 获取素有的li元素
        Elements elements = element.getElementsByTag("li");
        // 获取元素中的内容，这里的el就是li标签
        ArrayList<Content> goodsList = new ArrayList<>();
        for (Element el : elements) {
            // 关于图片特别多的网站，所有的图片都是延迟加载的（懒加载）
            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setTitle(title);
            content.setImg(img);
            content.setPrice(price);

            goodsList.add(content);
        }
        return goodsList;
    }
}
