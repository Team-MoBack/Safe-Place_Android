package org.techtown.moback.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.techtown.moback.R;
import org.techtown.moback.adapter.ViewPagerAdapter;
import org.techtown.moback.fragment.ChartFragment;
import org.techtown.moback.fragment.GraphFragment;
import org.techtown.moback.fragment.MyFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ChartFragment chartFragment = new ChartFragment();
    private GraphFragment graphFragment = new GraphFragment();
    private MyFragment myFragment = new MyFragment();

    private ViewPagerAdapter adapter;
    private ViewPager2 viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        adapter.addFragment(graphFragment);
        adapter.addFragment(chartFragment);
        adapter.addFragment(myFragment);

        viewPager = findViewById(R.id.viewpager_main);
        viewPager.setAdapter(adapter);

        viewPager.setUserInputEnabled(false);

        bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener((item) -> {
            switch (item.getItemId())
            {
                case R.id.navigation_1:
                    viewPager.setCurrentItem(0);
                  //  item.setIcon(getDrawable(R.drawable.mdi_map_select));
                    break;
                case R.id.navigation_2:
                    viewPager.setCurrentItem(1);
                    //item.setIcon(getDrawable(R.drawable.chart_select));
                    break;
                case R.id.navigation_3:
                    viewPager.setCurrentItem(2);
                  //  item.setIcon(getDrawable(R.drawable.my_select));
                    break;
            }

            return true;
        });
    }

    //맵 화면에서 하단 정보창 닫기
    @Override
    public void onBackPressed() {

        if(viewPager.getCurrentItem() == 0 && graphFragment.isVisibleInfoLayout())
            graphFragment.hideBottomInfoWindow();
        else
            super.onBackPressed();
    }
}