package com.strongnguyen.nguontruyen.ui.local;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import com.strongnguyen.nguontruyen.data.source.BooksRepository;

public class LocalViewModel extends AndroidViewModel {

    private final Context mContext;

    private final BooksRepository mBooksRepository;

    public LocalViewModel(Application context, BooksRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mBooksRepository = repository;

    }
}
