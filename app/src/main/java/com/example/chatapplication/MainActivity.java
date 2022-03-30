//jannat
package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity<ActivityMainBinding> extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =DataBindingUtil.setContenView(activity this,R.layout.activity_main);
    }
    private void setUpwithViewpager(viewpager viewpager){
      MainActivity.SectionspagerAdapter adapter=new SectionspagerAdapter(getSupportFragmentManager());
      //we need 3 fragment
        viewpager.setAdapter(adapter);
    }
    //add this code
    private static class SectionspagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final list<String> mFragmentTittleList = new ArrayList<>();

        public SectionspagerAdapter(FragmentManager manager) {super(manager) ;}
        @Override
        public Fragment getIteam(int position){return mFragmentList.get(position);}
        @Override
        public  int getCount(){return mFragmentList.size();}
        public void addFragment(fragment,String ,little) {
            mFragmentList.add(fragment);
            mFragmentTittleListList.add(title)
        }

        @Override
        public charSequence getpageTittle(int position) {return mFragmentTittleListList.grt(position);}
    }
}