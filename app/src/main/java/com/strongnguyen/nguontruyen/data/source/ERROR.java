package com.strongnguyen.nguontruyen.data.source;

import android.support.annotation.StringRes;

import com.strongnguyen.nguontruyen.R;

/**
 * The Class
 * Created by pc on 10/23/2018.
 */
public final class ERROR {
    public static final int NO_DATA = 1;
    public static final int NO_INTERNET = 2;
    public static final int LOAD_FAILED = 3;


    @StringRes
    public static int getErrorMsgRes (int errorCode) {

        switch (errorCode) {
            default: return R.string.error_undefined;
            case NO_DATA: return R.string.error_no_data;
            case NO_INTERNET: return R.string.error_no_internet;
            case LOAD_FAILED: return R.string.error_load_failed;
        }
    }
}
