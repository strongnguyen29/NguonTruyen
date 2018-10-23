package com.strongnguyen.nguontruyen.data.source.remote;

import android.support.annotation.NonNull;

import com.strongnguyen.nguontruyen.data.Filter;

import java.util.List;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/23/2018.
 * Email: vancuong2941989@gmail.com
 */
public class TruyenCvDataSource implements BooksOnlineDataSource {

    private static final String TAG = TruyenCvDataSource.class.getSimpleName();

    private static final String URL_PAGE = "https://truyencv.com/";

    private volatile static TruyenCvDataSource INSTANCE;

    /**
     * Get instance;
     * <p></p>
     * @return {@link TruyenCvDataSource}
     */
    public static TruyenCvDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (TruyenCvDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TruyenCvDataSource();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Destroy instance;
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getOnlineBooks(@NonNull List<Filter> filters, int page, @NonNull LoadOnlineBooksCallback callback) {

    }

    @Override
    public void getOnlineBook(@NonNull String urlBook) {

    }
}
