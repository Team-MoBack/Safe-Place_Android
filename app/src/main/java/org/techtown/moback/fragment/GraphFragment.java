package org.techtown.moback.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.techtown.moback.R;


public class GraphFragment extends Fragment {


    private View view;
    private GoogleMap mMap;

    public GraphFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_graph, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;

            LatLng SEOUL = new LatLng(37.56, 126.97);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(SEOUL);
            markerOptions.title("서울");
            markerOptions.snippet("한국의 수도");
            mMap.addMarker(markerOptions);


            // 기존에 사용하던 다음 2줄은 문제가 있습니다.

            // CameraUpdateFactory.zoomTo가 오동작하네요.
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
            //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));
        });

        return view;
    }
}