package com.example.clement.readingmood.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class MyAdapterFragment extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> listFragment =  new ArrayList<Fragment>();
    /*
    Fragments for the TabLayout
     */
    private final ArrayList<String> listTitle = new ArrayList<String>();
    /*
    Titles for the TabLayout
     */

    public MyAdapterFragment(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    public void addFragment(Fragment fragment, String title)
    {

        listFragment.add(fragment);
        listTitle.add(title);

    }




}

