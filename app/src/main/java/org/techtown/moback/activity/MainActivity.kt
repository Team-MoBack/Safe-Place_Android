package org.techtown.moback.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.moback.R
import org.techtown.moback.adapter.ViewPagerAdapter
import org.techtown.moback.fragment.ChartFragment
import org.techtown.moback.fragment.GraphFragment
import org.techtown.moback.fragment.MyFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private val chartFragment = ChartFragment()
    private val graphFragment = GraphFragment()
    private val myFragment = MyFragment()
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(graphFragment)
        adapter.addFragment(chartFragment)
        adapter.addFragment(myFragment)

        viewPager = findViewById(R.id.viewpager_main)
        viewPager.setAdapter(adapter)
        viewPager.setUserInputEnabled(false)

        bottomNavigationView = findViewById(R.id.bottomnavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item: MenuItem ->

            when (item.itemId) {
                R.id.navigation_1 -> viewPager.setCurrentItem(0)
                R.id.navigation_2 -> viewPager.setCurrentItem(1)
                R.id.navigation_3 -> viewPager.setCurrentItem(2)
            }

            true
        })
    }

    //맵 화면에서 하단 정보창 닫기
    override fun onBackPressed() {
        if (viewPager.currentItem == 0 && graphFragment.isVisibleInfoLayout)
            graphFragment.hideBottomInfoWindow()
        else
            super.onBackPressed()
    }
}