package com.dyasirvan.dicoding.moviecatalogue.adapter;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dyasirvan.dicoding.moviecatalogue.MainActivity;
import com.dyasirvan.dicoding.moviecatalogue.R;
import com.dyasirvan.dicoding.moviecatalogue.ui.MoviesFragment;
import com.dyasirvan.dicoding.moviecatalogue.ui.TvShowsFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    public String title="Movies";

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.title_movies,
            R.string.title_tvshows
    };
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MoviesFragment();
                break;
            case 1:
                fragment = new TvShowsFragment();
                break;
        }
        return fragment;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
    @Override
    public int getCount() {
        return 2;
    }
}
