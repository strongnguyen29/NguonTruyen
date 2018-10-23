package com.strongnguyen.nguontruyen.data.source.remote;

import android.support.annotation.NonNull;

import com.strongnguyen.nguontruyen.data.Book;
import com.strongnguyen.nguontruyen.data.Filter;
import com.strongnguyen.nguontruyen.data.FilterBook;
import com.strongnguyen.nguontruyen.data.source.ERROR;

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
    public void getOnlineBooks(@NonNull List<Filter> filters, int page, @NonNull
            LoadOnlineBooksCallback callback) {

        String urlLoad = buildOnlineBooks(filters, page);

        try {
            Document document = Jsoup.connect(urlLoad).get();
            if (document != null) {
                int totalPage = getTotalPage(document);

                if (page <= totalPage) {
                    callback.onBooksLoaded(
                            getListBooks(document), getFilterBooks(document), totalPage);
                } else {
                    callback.onFailure(ERROR.NO_DATA);
                }
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        callback.onFailure(ERROR.LOAD_FAILED);
    }

    @Override
    public void getOnlineBook(@NonNull String urlBook) {

    }

    /**
     * Build Url load list book from TruyenFull;
     * <p>
     *     Full url = https://truyenfull.vn/the-loai/kiem-hiep/hoan/trang-2/
     * </p>
     * @param filters path, filter;
     * @param page page;
     * @return Url load;
     */
    public String buildOnlineBooks(@NonNull List<Filter> filters, int page) {
        // Build url load; full url = https://truyenfull.vn/the-loai/kiem-hiep/hoan/trang-2/
        StringBuilder urlLoad = new StringBuilder(URL_PAGE);

        // Exp: https://truyenfull.vn/the-loai/kiem-hiep/
        for (Filter filter : filters) {
            urlLoad.append(filter.getValue());
            urlLoad.append("/");
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
    public List<Book> getListBooks(@NonNull Document document) {

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
            book.setTotalChapter(element.select(".text-info a").first().text());

            listBooks.add(book);
        }

        return listBooks;
    }

    /**
     * Get list filter on Document html;
     * @param document Doc html
     * @return list;
     */
    public List<FilterBook> getFilterBooks(@NonNull Document document) {
        ArrayList<FilterBook> filterBooks = new ArrayList<>();
        Elements listElement = document.select("ul.navbar-nav > li.dropdown");

        for (Element element : listElement) {
            FilterBook filterBook = new FilterBook();

            filterBook.setLabel(element.select("a.dropdown-toggle").first().text());

            ArrayList<Filter> listFilters = new ArrayList<>();

            Elements listElmFilters = element.select("ul > li > a");
            for (Element element2 : listElmFilters) {

                Filter filter = new Filter();
                filter.setTitle(element2.text());
                try {
                    String link = element2.attr("href");
                    URL url = new URL(link);
                    String path = url.getPath();
                    path = path.substring(1, path.length() - 1);
                    filter.setValue(path);
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
    public int getTotalPage(@NonNull Document document) {
        int totalPage = 1;

        Elements elements = document.select(".pagination li");

        if (elements == null || elements.size() < 2) {
            return totalPage;
        }

        if (elements.last().hasClass("page-nav")) {
            // Exist form select page, remove it.
            elements.remove(elements.get(elements.size()-1));
        }

        Element elementTotal = elements.last();
        if (!elementTotal.select(".arrow").isEmpty()) {
            // is exist btn last page: "Cuối"
            String textTotal = elementTotal.select("a").first().attr("title");
            String[] strArr = textTotal.split("Trang");
            textTotal = strArr[strArr.length -1];
            totalPage = Integer.parseInt(textTotal.replaceAll("[^0-9]", ""));

        } else if (!elementTotal.select(".glyphicon-menu-right").isEmpty()) {
            // is exist btn next page: ">", select btn left of this btn;
            String text = elements.get(elements.size() - 2).text();
            totalPage = Integer.parseInt(text.replaceAll("[^0-9]", ""));

        } else if (elementTotal.hasClass("active")) {
            // is btn last page;
            String text = elementTotal.text();
            totalPage = Integer.parseInt(text.replaceAll("[^0-9]", ""));

        }

        return totalPage;
    }
}
