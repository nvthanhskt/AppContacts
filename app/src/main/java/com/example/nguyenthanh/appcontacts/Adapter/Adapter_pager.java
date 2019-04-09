package com.example.nguyenthanh.appcontacts.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.nguyenthanh.appcontacts.Fragment.DanhBaFragment;
import com.example.nguyenthanh.appcontacts.Fragment.NhomFragment;
import com.example.nguyenthanh.appcontacts.Fragment.YeuThich_Fragment;

public class Adapter_pager extends FragmentStatePagerAdapter {
    private  static  String [] title={"Danh bạ","Yêu thích","Nhóm"};
    public Adapter_pager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:{
                return new DanhBaFragment();
            }
            case 1:{
                return new YeuThich_Fragment();
            }
            case 2:{
                return new NhomFragment();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
