package org.techtown.moback.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.techtown.moback.R;
import org.techtown.moback.adapter.ChartListAdapter;
import org.techtown.moback.adapter.ViewPagerAdapter;
import org.techtown.moback.model.ChartModel;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    public ChartFragment() {

    }

    private View view;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter adapter;

    private LocalFragment localFragment;
    private AllFragment allFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_chart, container, false);

        localFragment = new LocalFragment();
        allFragment = new AllFragment();

        viewPager = view.findViewById(R.id.viewpager_chart);
        viewPager.setUserInputEnabled(false);

        adapter = new ViewPagerAdapter(getFragmentManager(), getLifecycle());
        adapter.addFragment(localFragment);
        adapter.addFragment(allFragment);

        viewPager.setAdapter(adapter);

        tabLayout = view.findViewById(R.id.tablayout_chart);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();

                viewPager.setCurrentItem(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }
}