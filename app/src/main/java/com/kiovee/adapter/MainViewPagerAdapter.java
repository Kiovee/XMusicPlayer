package com.kiovee.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<Fragment> mFragmentSparseArray;

    public MainViewPagerAdapter(FragmentManager fm, SparseArray<Fragment> fragmentSparseArray) {
        super(fm);
        this.mFragmentSparseArray = fragmentSparseArray;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentSparseArray.get(position);
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mFragmentSparseArray.size();
    }
}
