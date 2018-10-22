package com.strongnguyen.nguontruyen.data.source.remote;

import android.support.annotation.NonNull;

import com.strongnguyen.nguontruyen.data.Book;
import com.strongnguyen.nguontruyen.data.FilterBook;

import java.util.List;


/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/22/2018.
 * Email: vancuong2941989@gmail.com
 */
public interface BooksOnlineDataSource {

    interface LoadOnlineBooksCallback {

        void onBooksLoaded(List<Book> books, List<FilterBook> filterBooks, int totalPage);

        void onFailure(String mes);
    }

    void getOnlineBooks(@NonNull List<FilterBook> filterBooks, int page, @NonNull LoadOnlineBooksCallback callback);

    void getOnlineBook(@NonNull String urlBook);
}
