package com.strongnguyen.nguontruyen.data.source.local;

import android.support.annotation.NonNull;

import com.strongnguyen.nguontruyen.data.Book;

import java.util.List;


/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/21/2018.
 * Email: vancuong2941989@gmail.com
 */
public interface BooksOfflineDataSource {

    interface LoadOfflineBooksCallback {

        void onBooksLoaded(List<Book> books);

        void onFailure(String mes);
    }

    void getOfflineBooks(@NonNull LoadOfflineBooksCallback callback);
}
