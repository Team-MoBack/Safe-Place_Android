package org.techtown.moback.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_all.*
import kotlinx.android.synthetic.main.fragment_local.*
import org.techtown.moback.R
import org.techtown.moback.adapter.ChartListAdapter
import org.techtown.moback.model.ChartModel
import java.util.*

class LocalFragment : Fragment() {

    private val TAG = "LocalFragment"
    private lateinit var adapter: ChartListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = ChartListAdapter()
        val list: MutableList<ChartModel> = ArrayList()

        //TODO : 테스트 코드
        list.add(ChartModel(1, activity!!.getDrawable(R.drawable.junction)!!, "Park", "great", 1000))
        list.add(ChartModel(2, activity!!.getDrawable(R.drawable.junction)!!, "Kim", "great", 2000))

        //구분선 적용
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recycler_view_local.addItemDecoration(dividerItemDecoration)
        recycler_view_local.setAdapter(adapter)
        adapter.submitList(list)
    }
}