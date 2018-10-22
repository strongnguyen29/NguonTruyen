package com.strongnguyen.nguontruyen.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.strongnguyen.nguontruyen.R;
import com.strongnguyen.nguontruyen.ui.local.LocalFragment;
import com.strongnguyen.nguontruyen.ui.review.ReviewFragment;
import com.strongnguyen.nguontruyen.ui.setting.SettingFragment;
import com.strongnguyen.nguontruyen.ui.source.SourceFragment;
import com.strongnguyen.nguontruyen.util.LogUtil;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private BottomNavigationView bottomNav;

    private ViewPager viewPager;

    private ViewGroup loadingView;

    private MainViewPagerAdaper viewPagerAdaper;

    private MenuItem prevMenuItemNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewPager();

        setBottomNav();

        setLoadingView();
    }

    /**
     * Show / hide loading view;
     * @param show bool
     */
    public void showLoading(boolean show) {
        loadingView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * Setup viewpager;
     */
    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.act_main_vp);
        viewPagerAdaper = new MainViewPagerAdaper(getSupportFragmentManager());
        viewPagerAdaper.addFragment(new SourceFragment());
        viewPagerAdaper.addFragment(new ReviewFragment());
        viewPagerAdaper.addFragment(new LocalFragment());
        viewPagerAdaper.addFragment(new SettingFragment());
        viewPager.setAdapter(viewPagerAdaper);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItemNav != null) {
                    prevMenuItemNav.setChecked(false);
                }
                else
                {
                    bottomNav.getMenu().getItem(0).setChecked(false);
                }
                LogUtil.d(TAG, "onPageSelected: " + position);
                bottomNav.getMenu().getItem(position).setChecked(true);
                prevMenuItemNav = bottomNav.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Setup bottom navigation view
     */
    private void setBottomNav () {
        bottomNav = (BottomNavigationView) findViewById(R.id.act_main_menu_bottom);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_source_book:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_review_book:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_download_book:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_setting:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Setup Loading view;
     */
    private void setLoadingView() {
        loadingView = (ViewGroup) findViewById(R.id.act_main_layout_loading);
        loadingView.setVisibility(View.GONE);
    }

}
