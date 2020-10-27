package org.techtown.moback.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_all.*
import org.techtown.moback.R
import org.techtown.moback.adapter.ChartListAdapter
import org.techtown.moback.model.ChartModel
import java.util.*

class AllFragment : Fragment() {


    private lateinit var adapter: ChartListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_all, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ChartListAdapter()
        val list: MutableList<ChartModel> = ArrayList()

        //TODO : 테스트 코드
        list.add(ChartModel(1, activity!!.getDrawable(R.drawable.junction)!!, "Park", "great", 10030))
        list.add(ChartModel(2, activity!!.getDrawable(R.drawable.junction)!!, "Kim", "Excellent", 2050))
        list.add(ChartModel(3, activity!!.getDrawable(R.drawable.junction)!!, "Lee", "Perfect", 5560))
        list.add(ChartModel(4, activity!!.getDrawable(R.drawable.junction)!!, "Rosa", "Gold", 15000))
        list.add(ChartModel(5, activity!!.getDrawable(R.drawable.junction)!!, "Roi", "Silver", 500))

        //구분선 적용
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recycler_view_all.addItemDecoration(dividerItemDecoration)
        recycler_view_all.setAdapter(adapter)
        adapter.submitList(list)
    }
}