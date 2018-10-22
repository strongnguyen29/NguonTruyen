package com.strongnguyen.nguontruyen.ui.setting;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import com.strongnguyen.nguontruyen.data.source.BooksRepository;

public class SettingViewModel extends AndroidViewModel

    {

        private final Context mContext;

        private final BooksRepository mBooksRepository;

    public SettingViewModel(Application context, BooksRepository repository) {
            super(context);
            mContext = context.getApplicationContext(); // Force use of Application Context.
            mBooksRepository = repository;

        }
}
