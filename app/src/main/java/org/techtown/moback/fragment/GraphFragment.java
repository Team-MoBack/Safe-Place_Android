package org.techtown.moback.fragment;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.techtown.moback.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class GraphFragment extends Fragment implements PlacesListener, OnMapReadyCallback {


    private View view;
    private GoogleMap mMap;
    private FloatingActionButton myLocation_btn;
    private List<Marker> previous_marker = null;

    private ConstraintLayout bottom_info_layout;
    private ImageButton favorite_btn;
    private Button getseat_btn;

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

        previous_marker = new ArrayList<Marker>();
        bottom_info_layout = view.findViewById(R.id.storeinfo_layout);
        favorite_btn = view.findViewById(R.id.favorite_btn_graph);
        getseat_btn = view.findViewById(R.id.getseat_btn_graph);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //현재 위치
        myLocation_btn = view.findViewById(R.id.current_position_fab_graph);
        myLocation_btn.setOnClickListener(view1 -> {
            Location location = getMyLocation();
            if(location != null)
            {
                double lng = location.getLongitude();
                double lat = location.getLatitude();

                LatLng position = new LatLng(lat, lng);
                addMarkerInMap(position, "현재 위치", getAddress(position));
                moveCameraTo(position);
                showPlaceInformation(position);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        mMap.setOnMapClickListener(latLng -> {
            hideBottomInfoWindow();
            getseat_btn.setBackground(getActivity().getDrawable(R.drawable.ripple_bg_gray));
        });

        mMap.setOnMarkerClickListener(marker -> {
            //TODO : 마커 클릭 시 반응
            marker.showInfoWindow();

            //하단 정보 레이아웃
            showBottomInfoWindow();
            getseat_btn.setBackground(getActivity().getDrawable(R.drawable.ripple_bg_primary));
            return true;
        });

        Location location = getMyLocation();
        if(location != null)
        {
            double lng = location.getLongitude();
            double lat = location.getLatitude();

            LatLng position = new LatLng(lat, lng);
            addMarkerInMap(position, "현재 위치" ,getAddress(position));
            moveCameraTo(position);
            showPlaceInformation(position);
        }
    }

    private void showBottomInfoWindow()
    {
        bottom_info_layout.setVisibility(View.VISIBLE);
    }

    private void hideBottomInfoWindow()
    {
        bottom_info_layout.setVisibility(View.GONE);
    }


    private Location getMyLocation()
    {

        try {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            Location location;
            //네트워크 연결 O -> 네트워크
            if(networkInfo != null && networkInfo.isConnected())
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                //네트워크 연결 X -> GPS
            else
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            return location;
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private Marker addMarkerInMap(LatLng position, String title, String snipet)
    {
        //내 위치에 마커표시
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.title(title);
        markerOptions.snippet(snipet);
        return mMap.addMarker(markerOptions);
    }

    private void moveCameraTo(LatLng position)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));
    }

    private String getAddress(LatLng latLng)
    {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latLng.latitude,
                    latLng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(getContext(), "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(getContext(), "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(getContext(), "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }
    }

    public void showPlaceInformation(LatLng location)
    {
        mMap.clear();//지도 클리어

        if (previous_marker != null)
            previous_marker.clear();//지역정보 마커 클리어

        new NRPlaces.Builder()
                .listener(this)
                .key("AIzaSyBIlMDJQLVxv6tNxqDyxAk5ErqPNm5Qmts")
                .latlng(location.latitude, location.longitude)//현재 위치
                .radius(500) //500 미터 내에서 검색
                .type(PlaceType.CAFE) //카페
                .build()
                .execute();
    }

    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(List<Place> places) {
        getActivity().runOnUiThread(() -> {
            for (noman.googleplaces.Place place : places) {

                LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());

                String markerSnippet = getAddress(latLng);
                Marker item = addMarkerInMap(latLng, place.getName(), markerSnippet);
                previous_marker.add(item);
            }

            //중복 마커 제거
            HashSet<Marker> hashSet = new HashSet<Marker>();
            hashSet.addAll(previous_marker);
            previous_marker.clear();
            previous_marker.addAll(hashSet);

        });
    }

    @Override
    public void onPlacesFinished() {

    }

}