package com.strongnguyen.nguontruyen;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.VisibleForTesting;

import com.strongnguyen.nguontruyen.data.source.BooksRepository;
import com.strongnguyen.nguontruyen.ui.local.LocalViewModel;
import com.strongnguyen.nguontruyen.ui.review.ReviewViewModel;
import com.strongnguyen.nguontruyen.ui.setting.SettingViewModel;
import com.strongnguyen.nguontruyen.ui.source.SourceViewModel;

/**
 * A creator is used to inject the product ID into the ViewModel
 * <p>
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 * <p>
 * Created by Mr Cuong on 10/22/2018.
 * Email: vancuong2941989@gmail.com
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final BooksRepository mBooksRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application, BooksRepository.getInstance());
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, BooksRepository repository) {
        mApplication = application;
        mBooksRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SourceViewModel.class)) {

            //noinspection unchecked
            return (T) new SourceViewModel(mApplication, mBooksRepository);
        } else if (modelClass.isAssignableFrom(ReviewViewModel.class)) {

            //noinspection unchecked
            return (T) new ReviewViewModel(mApplication, mBooksRepository);
        } else if (modelClass.isAssignableFrom(LocalViewModel.class)) {

            //noinspection unchecked
            return (T) new LocalViewModel(mApplication, mBooksRepository);
        } else if (modelClass.isAssignableFrom(SettingViewModel.class)) {

            //noinspection unchecked
            return (T) new SettingViewModel(mApplication, mBooksRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
