package com.strongnguyen.nguontruyen.data.source;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.strongnguyen.nguontruyen.data.Filter;
import com.strongnguyen.nguontruyen.data.source.remote.BooksOnlineDataSource;
import com.strongnguyen.nguontruyen.data.source.remote.TruyenFullDataSource;

import java.util.List;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/21/2018.
 * Email: vancuong2941989@gmail.com
 */
public class BooksRepository implements BooksOnlineDataSource {

    private static final String TAG = BooksRepository.class.getSimpleName();

    public static final int TRUYEN_FULL = 0;

    public static final int TRUYEN_CV = 1;

    private volatile static BooksRepository INSTANCE = null;

    private LoadOnlineBooksAsync mLoadOnlineBooksAsync;

    public static int sourceBook = 0;

    // Get instance;
    public static BooksRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (BooksRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BooksRepository();
                }
            }
        }
        return INSTANCE;
    }

    // Destroy Instance
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getOnlineBooks(List<Filter> filters, int page, @NonNull LoadOnlineBooksCallback callback) {
        mLoadOnlineBooksAsync = new LoadOnlineBooksAsync(filters, page, callback);
        mLoadOnlineBooksAsync.execute();
    }

    @Override
    public void getOnlineBook(@NonNull String urlBook) {

    }


    /**
     * Asynctask load list book;
     */
    private static class LoadOnlineBooksAsync extends AsyncTask<Void, Void, Void> {

        private List<Filter> filters;

        private int page;

        private LoadOnlineBooksCallback callback;

        public LoadOnlineBooksAsync(List<Filter> filters, int page, LoadOnlineBooksCallback callback) {
            this.filters = filters;
            this.page = page;
            this.callback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            switch (sourceBook) {
                case TRUYEN_FULL:
                    TruyenFullDataSource.getInstance().getOnlineBooks(filters, page, callback);
                    break;
                case TRUYEN_CV:

                    break;
            }
            return null;
        }
    }
}
