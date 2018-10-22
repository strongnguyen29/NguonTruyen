package com.strongnguyen.nguontruyen.data.source.remote;

import android.support.annotation.NonNull;

import com.strongnguyen.nguontruyen.data.Book;
import com.strongnguyen.nguontruyen.data.Filter;
import com.strongnguyen.nguontruyen.data.FilterBook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/22/2018.
 * Email: vancuong2941989@gmail.com
 */
public class TruyenFullDataSource implements BooksOnlineDataSource {
    private static final String TAG = TruyenFullDataSource.class.getSimpleName();

    private static final String URL_PAGE = "https://truyenfull.vn/";

    private static final long EXPIRES_HASH = 30 * 60 * 1000; // Milisecond <=> 30 phut

    private static String hashKey;

    private static long timeGetHash; // Milisecond

    private volatile static TruyenFullDataSource INSTANCE;

    public static TruyenFullDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (TruyenFullDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TruyenFullDataSource();
                }
            }
        }
        return INSTANCE;
    }

    // Destroy instance;
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getOnlineBooks(@NonNull List<FilterBook> filterBooks, int page, @NonNull LoadOnlineBooksCallback callback) {

        String urlLoad = buildOnlineBooks(filterBooks, page);

        try {
            Document document = Jsoup.connect(urlLoad).get();
            if (document != null) {

                callback.onBooksLoaded(getListBooks(document),
                        getFilterBooks(document), getTotalPage(document));
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        callback.onFailure("Tải thất bại.");
    }

    @Override
    public void getOnlineBook(@NonNull String urlBook) {

    }

    /**
     * Build Url load list book from TruyenFull;
     * <p>
     *     Full url = https://truyenfull.vn/the-loai/kiem-hiep/hoan/trang-2/
     * </p>
     * @param filterBooks path, filter;
     * @param page page;
     * @return Url load;
     */
    private String buildOnlineBooks(@NonNull List<FilterBook> filterBooks, int page) {
        // Build url load; full url = https://truyenfull.vn/the-loai/kiem-hiep/hoan/trang-2/
        StringBuilder urlLoad = new StringBuilder(URL_PAGE);

        for (FilterBook filterBook : filterBooks) {
            // Add path to URL;
            if (filterBook.getPath().length() > 1) {
                // Exp: https://truyenfull.vn/danh-sach/
                urlLoad.append(filterBook.getPath());
                urlLoad.append("/");
                // Exp: https://truyenfull.vn/the-loai/kiem-hiep/
                for (Filter filter : filterBook.getFilters()) {
                    urlLoad.append(filter.getValue());
                    urlLoad.append("/");
                }
            }
        }

        // Add path page to URL;
        if (page > 1) {
            urlLoad.append("trang-");
            urlLoad.append(page);
            urlLoad.append("/");
        }

        return urlLoad.toString();
    }

    /**
     * Get list books on Document html;
     * <p></p>
     * @param document Doc html
     * @return list;
     */
    private List<Book> getListBooks(@NonNull Document document) {

        ArrayList<Book> listBooks = new ArrayList<>();

        Elements listElement = document.select(".col-truyen-main .list-truyen > div.row");

        for (Element element : listElement) {
            Book book = new Book();

            Element coverElement = element.select("div.lazyimg").first();
            if(coverElement != null) {
                book.setPoster(coverElement.attr("data-image"));
            }

            Element subjectElement = element.select("h3.truyen-title a").first();
            book.setTitle(subjectElement.text());
            book.setUrl(subjectElement.attr("href"));

            book.setFull(element.select("span.label-full").first() != null);

            book.setAuthor(element.select("span.author").first().text());
            book.setTotalChapter(element.select("span.chapter-text").first().text());

            listBooks.add(book);
        }

        return listBooks;
    }

    /**
     * Get list filter on Document html;
     * @param document Doc html
     * @return list;
     */
    private List<FilterBook> getFilterBooks(@NonNull Document document) {
        ArrayList<FilterBook> filterBooks = new ArrayList<>();
        Elements listElement = document.select("ul.navbar-nav > li.dropdown");

        for (int i = 0; i < listElement.size(); i++) {
            FilterBook filterBook = new FilterBook();

            Element element = listElement.get(i);
            if (i == 0) {
                filterBook.setLabel("Danh sách");
                filterBook.setPath("danh-sach");
            } else {
                filterBook.setLabel("Thể loại");
                filterBook.setPath("the-loai");
            }

            ArrayList<Filter> listFilters = new ArrayList<>();

            Elements listElmFilters = element.select("li > a");
            for (Element element2 : listElmFilters) {
                Filter filter = new Filter();
                filter.setTitle(element2.text());

                try {
                    String link = element2.attr("href");
                    URL url = new URL(link);
                    String[] paths = url.getPath().split("/");
                    filter.setValue(paths[2]);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                listFilters.add(filter);
            }

            filterBook.setFilters(listFilters);
            filterBooks.add(filterBook);
        }

        return filterBooks;
    }

    /**
     * Get total page on doc html;
     * @param document Doc html
     * @return int
     */
    private int getTotalPage(@NonNull Document document) {

        return 0;
    }
}
