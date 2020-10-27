package org.techtown.moback.model

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil

data class ChartModel(var rank_number: Int, var profile_image: Drawable, var user_name: String, var user_level: String,var user_point: Int) {

    fun getUser_point() : String = user_point.toString()

    fun getRank_number() : String = rank_number.toString()

    companion object {
        @JvmField
        var DIFF_CALLBACK: DiffUtil.ItemCallback<ChartModel> = object : DiffUtil.ItemCallback<ChartModel>() {
            override fun areItemsTheSame(oldItem: ChartModel, newItem: ChartModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ChartModel, newItem: ChartModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}