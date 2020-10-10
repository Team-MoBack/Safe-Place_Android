package org.techtown.moback.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.techtown.moback.R;
import org.techtown.moback.adapter.ChartListAdapter;
import org.techtown.moback.model.ChartModel;

import java.util.ArrayList;
import java.util.List;

public class AllFragment extends Fragment {

    private View view;

    private RecyclerView recyclerView;
    private ChartListAdapter adapter;

    public AllFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_all);
        adapter = new ChartListAdapter();

        List<ChartModel> list = new ArrayList<>();

        //TODO : 테스트 코드
        list.add(new ChartModel(1, getActivity().getDrawable(R.drawable.android), "Park", "great", 1000));
        list.add(new ChartModel(2, getActivity().getDrawable(R.drawable.android), "Kim", "great", 2000));

        //구분선 적용
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        adapter.submitList(list);

        return view;
    }
}