package com.strongnguyen.nguontruyen.data.source.remote;

import com.google.gson.Gson;
import com.strongnguyen.nguontruyen.data.Book;
import com.strongnguyen.nguontruyen.data.Filter;
import com.strongnguyen.nguontruyen.data.FilterBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class
 * Created by pc on 10/23/2018.
 */
public class TruyenFullDataSourceTest {

    TruyenFullDataSource mTruyenFull;
    Document document;

    @Before
    public void setUp() throws Exception {
        mTruyenFull = TruyenFullDataSource.getInstance();
    }

    @Test
    public void getOnlineBooks() {
        BooksOnlineDataSource.LoadOnlineBooksCallback callback = new BooksOnlineDataSource.LoadOnlineBooksCallback() {
            @Override
            public void onBooksLoaded(List<Book> books, List<FilterBook> filterBooks, int totalPage) {
                showMes("\nBooks -----------------\n" + new Gson().toJson(books));
                showMes("\nFilterBook -----------------\n" + new Gson().toJson(filterBooks));
                showMes("\nTotalPage = " + totalPage + " ---------------------");
            }

            @Override
            public void onFailure(int errorCode) {
                showMes("onFailure : " + errorCode + " ------------------");
            }
        };

        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("Tiên hiệp", "the-loai/tien-hiep"));

        String link = mTruyenFull.buildOnlineBooks(filters, 21);
        showMes("\nLink : " + link + " -----------");
        mTruyenFull.getOnlineBooks(filters, 21, callback);
    }

    @Test
    public void getOnlineBook() {
    }

    @Test
    public void buildOnlineBooks() {

        ArrayList<Filter> filters = new ArrayList<>();

        filters.add(new Filter("Tiên hiệp", "the-loai/tien-hiep"));

        int page = 1;
        showMes("\nBuild link page = " + page);
        showMes(mTruyenFull.buildOnlineBooks(filters, page));

        page = 3;
        showMes("\nBuild link page = " + page);
        showMes(mTruyenFull.buildOnlineBooks(filters, page));

        filters.add(new Filter("Hoàn thành", "hoan"));
        showMes("\nBuild link page = " + page);
        showMes(mTruyenFull.buildOnlineBooks(filters, page));

        page = 1;
        showMes("\nBuild link page = " + page);
        showMes(mTruyenFull.buildOnlineBooks(filters, page));
    }

    @Test
    public void getListBooks() throws IOException {
        String link = "https://truyenfull.vn/danh-sach/truyen-hot/";
        String link2 = "https://truyenfull.vn/danh-sach/truyen-hot/trang-642/";

        List<Book> listBook = new ArrayList<>();
        Document document = Jsoup.connect(link2).get();
        listBook = mTruyenFull.getListBooks(document);

        if (listBook.size() == 0) {
            showMes("Không có dữ liệu");
            return;
        }

        for (Book book : listBook) {
            showMes("\nBook id = " + book.getIdBook() + " ------------");
            showMes("Title = " + book.getTitle());
            showMes("poster = " + book.getPoster());
            showMes("Author = " + book.getAuthor());
            showMes("Total chapter = " + book.getTotalChapter());
        }
    }

    @Test
    public void getFilterBooks() throws IOException {
        String link = "https://truyenfull.vn/danh-sach/truyen-hot/";
        List<FilterBook> filterBookList = new ArrayList<>();

        Document document = Jsoup.connect(link).get();
        filterBookList = mTruyenFull.getFilterBooks(document);

        String json = new Gson().toJson(filterBookList);
        showMes(json);
    }

    @Test
    public void getTotalPage() throws IOException {

        String[] urlArr = new String[] {
                "https://truyenfull.vn/danh-sach/truyen-hot/",
                "https://truyenfull.vn/danh-sach/truyen-hot/trang-639/",
                "https://truyenfull.vn/danh-sach/truyen-hot/trang-641/",
                "https://truyenfull.vn/the-loai/viet-nam/",
                "https://truyenfull.vn/the-loai/light-novel/"
        };

        for (int i = 0; i < urlArr.length; i++) {
            showMes("\nLink " + i + " -- " + urlArr[i]);
            document = Jsoup.connect(urlArr[i]).get();
            if (document == null) showMes("Doc is NULL");

            int totalPage = mTruyenFull.getTotalPage(document);
            showMes("totalPage: " + totalPage);
        }
    }

    private void showMes(String s) {
        System.out.println(s == null ? "NULL" : s);
    }

    @Test
    public void getFilterBooksTest() throws IOException {
        ArrayList<FilterBook> filterBooks = new ArrayList<>();

        String link = "https://truyenfull.vn/danh-sach/truyen-hot/";
        Document document = Jsoup.connect(link).get();
        Elements listElement = document.select("ul.navbar-nav > li.dropdown");

        for (Element element : listElement) {
            FilterBook filterBook = new FilterBook();

            filterBook.setLabel(element.select("a.dropdown-toggle").first().text());

            ArrayList<Filter> listFilters = new ArrayList<>();

            Elements listElmFilters = element.select("ul > li > a");

            showMes("\nlistElmFilters----------- \n" + listElmFilters.html());
        }
    }
}