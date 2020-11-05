package org.techtown.moback.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.techtown.moback.MyViewHolder
import org.techtown.moback.R
import org.techtown.moback.databinding.ChartItemBinding
import org.techtown.moback.model.ChartModel

class ChartListAdapter  : ListAdapter<ChartModel, MyViewHolder<ChartItemBinding>>(ChartModel.DIFF_CALLBACK) {

    private val TAG = "ChartListAdapter"

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder<ChartItemBinding> {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater.inflate(R.layout.chart_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder<ChartItemBinding>, position: Int) {
        holder.binding().model = getItem(position)
    }
}