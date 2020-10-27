package org.techtown.moback.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_chart.*
import org.techtown.moback.R
import org.techtown.moback.adapter.ViewPagerAdapter

class ChartFragment : Fragment() {


    private lateinit var adapter: ViewPagerAdapter
    private lateinit var localFragment: LocalFragment
    private lateinit var allFragment: AllFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        localFragment = LocalFragment()
        allFragment = AllFragment()

        viewpager_chart.setUserInputEnabled(false)

        adapter = ViewPagerAdapter(fragmentManager!!, lifecycle)
        adapter.addFragment(localFragment)
        adapter.addFragment(allFragment)

        viewpager_chart.setAdapter(adapter)

        tablayout_chart.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val pos = tab.position
                viewpager_chart.setCurrentItem(pos)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}