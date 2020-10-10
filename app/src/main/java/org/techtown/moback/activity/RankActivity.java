package org.techtown.moback.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import org.techtown.moback.R;
import org.techtown.moback.adapter.ViewPagerAdapter;

public class RankActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;



    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
    }

    //viewpager 및 어댑터 생성
    public void createViewpager()
    {
        viewPager = (ViewPager2) findViewById(R.id.viewpager_rank);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        // viewPagerAdapter.addFragment(mouseFragment);
        //viewPagerAdapter.addFragment(keyboardFragment);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setUserInputEnabled(false);//터치 스크롤 막음
    }
}