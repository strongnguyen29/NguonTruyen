package com.strongnguyen.nguontruyen.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 10/22/2018.
 * Email: vancuong2941989@gmail.com
 */
public class MainViewPagerAdaper extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();

    public MainViewPagerAdaper(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }
}
