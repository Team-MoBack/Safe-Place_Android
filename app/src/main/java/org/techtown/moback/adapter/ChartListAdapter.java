package org.techtown.moback.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.techtown.moback.MyViewHolder;
import org.techtown.moback.R;
import org.techtown.moback.databinding.ChartItemBinding;
import org.techtown.moback.model.ChartModel;

import java.util.zip.Inflater;

public class ChartListAdapter extends ListAdapter<ChartModel, MyViewHolder<ChartItemBinding>> {


    public ChartListAdapter() {
        super(ChartModel.DIFF_CALLBACK);
    }

    @Override
    public int getItemCount() {
        return getCurrentList().size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder<>(inflater.inflate(R.layout.chart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder<ChartItemBinding> holder, int position) {
        holder.binding().setModel(getItem(position));
    }
}
