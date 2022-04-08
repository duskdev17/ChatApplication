package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chatapplication.databinding.ActivityMainBinding;
import com.example.chatapplication.menu.CallsFragment;
import com.example.chatapplication.menu.ChatsFragment;
import com.example.chatapplication.menu.StatusFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpWithViewPager(binding.viewPager);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        setSupportActionBar(binding.toolbar);
    }
//sumi
    bindig.viewpage.addOnPageChangeiistener(new viewPager.OnPageChangeiistener(){
        @Override
                public void OnPagescrolled(int position, float positionOffset, int positionOffsetpixels)
        }
        @Override
    public void OnPagescrolled(int position) {
        changFabICon(posion);
        }
        @Override
    public void OnPagescrollstateChanged(int state) {
        }
        }};
    private void setUpWithViewPager(ViewPager viewPager){
      MainActivity.SectionsPagerAdapter adapter=new SectionsPagerAdapter(getSupportFragmentManager());
      adapter.addFragment(new ChatsFragment(), "Chats");
      adapter.addFragment(new StatusFragment(), "Status");
      adapter.addFragment(new CallsFragment(), "Calls");

        //we need 3 fragments
        viewPager.setAdapter(adapter);
    }

    //add this code
    private static class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {super(manager) ;}

        @Override
        public Fragment getItem(int position){return mFragmentList.get(position);}

        @Override
        public  int getCount(){return mFragmentList.size();}

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {return mFragmentTitleList.get(position);}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle actionbar item click here. The actionbar will
        //automatically handle clicks on the Home/Up button, so
        //long as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        switch (id){
            case R.id.menu_search: Toast.makeText(MainActivity.this, "Action Search", Toast.LENGTH_LONG).show(); break;
            case R.id.menu_more: Toast.makeText(MainActivity.this, "Action More", Toast.LENGTH_LONG).show(); break;
        }
        return super.onOptionsItemSelected(item);
    }
    //sumi
    private void changFabICon(final int index)
        binding.fabAction.hide()
                new handler().postDelayed(new Runnable() {
        @Override
        public void run () {
            switch (index) {
                case 0: binding.fabAction.setImagedrawable(getDrawable(R.drawable.ic_chat_black_24));break;
                case 1: binding.fabAction.setImagedrawable(getDrawable(R.drawable.ic_camera_black_24));break;
                case 2: binding.fabAction.setImagedrawable(getDrawable(R.drawable.ic_call_black_24));break;
            }
            binding.fabAction.show();
        }
    }.delaymillis 400);
    }


}