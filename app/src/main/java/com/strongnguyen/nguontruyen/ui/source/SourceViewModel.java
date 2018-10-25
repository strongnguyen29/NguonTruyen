package com.strongnguyen.nguontruyen.ui.source;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;

import com.strongnguyen.nguontruyen.SingleLiveEvent;
import com.strongnguyen.nguontruyen.data.Book;
import com.strongnguyen.nguontruyen.data.source.BooksRepository;

import java.util.List;

public class SourceViewModel extends AndroidViewModel {

    private final Context mContext;

    private final BooksRepository mBooksRepository;

    private final ObservableField<String> textMsg = new ObservableField<>();

    private final ObservableField<Boolean> enabledMsg = new ObservableField<>();

    private final ObservableField<SourceRecyclerAdapter> adapter = new ObservableField<>();

    private SingleLiveEvent<List<Book>> listBook;

    public SourceViewModel(Application context, BooksRepository repository) {
        super(context);
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mBooksRepository = repository;
    }

    public ObservableField<String> getTextMsg() {
        return textMsg;
    }

    public ObservableField<Boolean> getEnabledMsg() {
        return enabledMsg;
    }

    public ObservableField<SourceRecyclerAdapter> getAdapter() {
        return adapter;
    }
}