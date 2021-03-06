package com.example.project1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentStatePagerSupport extends FragmentStatePagerAdapter {

    //Mostly obtained from developer documents https://developer.android.com/reference/android/support/v4/app/FragmentStatePagerAdapter

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public FragmentStatePagerSupport(FragmentManager fm) {
        super(fm);
    }

    //Function to add fragments and titles to arrays
    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    //getting for position of fragment
    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    //determines the size of array
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}