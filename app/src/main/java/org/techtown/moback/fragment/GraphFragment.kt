package org.techtown.moback.fragment

import android.content.Context
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.fragment_graph.*
import noman.googleplaces.*
import org.techtown.moback.R
import java.io.IOException
import java.util.*

class GraphFragment : Fragment(), PlacesListener, OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    private var previous_marker: MutableList<Marker>? = null

    private var isFavorite = false
    private val radius = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        previous_marker = ArrayList()

        favorite_btn_graph.setClickable(false)
        getseat_btn_graph.setClickable(false)

        //가게 좋아요 버튼
        favorite_btn_graph.setOnClickListener( { view1: View? ->
            //TODO : 추후에 수정
            if (!isFavorite) {
                isFavorite = true
                favorite_btn_graph.setImageDrawable(activity!!.getDrawable(R.drawable.ic_baseline_favorite_24))
            } else {
                isFavorite = false
                favorite_btn_graph.setImageDrawable(activity!!.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            }
        })
        val mapFragment = childFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        //현재 위치
        current_position_fab_graph.setOnClickListener(View.OnClickListener { view1: View? ->
            val latLng = toLatLng(myLocation)
            showPlaceInformation(latLng)
            goToMyLocation()
        })
    }

    val isVisibleInfoLayout: Boolean
        get() = if (storeinfo_layout.visibility == View.VISIBLE) true else false

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //마커가 아닌곳 누를 땐 하단 레이아웃 숨김
        mMap!!.setOnMapClickListener { latLng: LatLng? ->
            hideBottomInfoWindow()
            getseat_btn_graph.isClickable = false
            favorite_btn_graph.isClickable = false
        }

        //정보레이아웃 보이기
        mMap!!.setOnMarkerClickListener { marker: Marker ->
            //마커 클릭 시 반응
            marker.showInfoWindow()

            //하단 정보 레이아웃
            showBottomInfoWindow()
            setStoreInfo(marker.title, marker.snippet)
            favorite_btn_graph.isClickable = true
            getseat_btn_graph.isClickable = true
            getseat_btn_graph.background = activity!!.getDrawable(R.drawable.ripple_bg_primary)
            true
        }
        val latLng = toLatLng(myLocation)
        showPlaceInformation(latLng)
        goToMyLocation()
    }

    private fun goToMyLocation() {
        val location = myLocation
        if (location != null) {
            val position = toLatLng(location)
            val circleOptions = CircleOptions().center(position)
                    .radius(radius.toDouble())
                    .strokeWidth(0f)
                    .fillColor(Color.parseColor("#4c6fea8a"))
            addMarkerInMap(position, "현재 위치", getAddress(position), R.drawable.now_i_am, circleOptions)
            moveCameraTo(position)
        }
    }

    private fun toLatLng(location: Location?): LatLng? {
        if (location != null) {
            val lng = location.longitude
            val lat = location.latitude
            return LatLng(lat, lng)
        }
        return null
    }

    private fun setStoreInfo(store_name: String, store_address: String) {
        storename_tv.text = store_name
        store_address_tv.text = store_address
    }

    private fun showBottomInfoWindow() {
        storeinfo_layout.visibility = View.VISIBLE
    }

    fun hideBottomInfoWindow() {
        favorite_btn_graph.setImageDrawable(activity!!.getDrawable(R.drawable.ic_baseline_favorite_border_24))
        getseat_btn_graph.background = activity!!.getDrawable(R.drawable.ripple_bg_gray)
        storeinfo_layout.visibility = View.GONE
    }

    //네트워크 연결 O -> 네트워크
    private val myLocation: Location?
        private get() {
            try {
                val locationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val connectivityManager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager.activeNetworkInfo

                //네트워크 연결 O -> 네트워크
                val location: Location = if (networkInfo != null && networkInfo.isConnected)
                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                else
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                return location
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
            return null
        }

    private fun addMarkerInMap(position: LatLng, title: String, snipet: String, icon_id: Int): Marker {
        //내 위치에 마커표시
        val markerOptions = MarkerOptions()
        markerOptions.icon(BitmapDescriptorFactory.fromResource(icon_id))
        markerOptions.position(position)
        markerOptions.title(title)
        markerOptions.snippet(snipet)
        return mMap!!.addMarker(markerOptions)
    }

    private fun addMarkerInMap(position: LatLng?, title: String, snipet: String, icon_id: Int, circleOptions: CircleOptions): Marker {
        //내 위치에 마커표시
        val markerOptions = MarkerOptions()
        markerOptions.icon(BitmapDescriptorFactory.fromResource(icon_id))
        markerOptions.position(position!!)
        markerOptions.title(title)
        markerOptions.snippet(snipet)
        mMap!!.addCircle(circleOptions)
        return mMap!!.addMarker(markerOptions)
    }

    private fun moveCameraTo(position: LatLng?) {
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
    }

    private fun getAddress(latLng: LatLng?): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>?
        addresses = try {
            geocoder.getFromLocation(
                    latLng!!.latitude,
                    latLng.longitude,
                    1)
        } catch (ioException: IOException) {
            //네트워크 문제
            Toast.makeText(context, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show()
            return "지오코더 서비스 사용불가"
        } catch (illegalArgumentException: IllegalArgumentException) {
            Toast.makeText(context, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show()
            return "잘못된 GPS 좌표"
        }
        return if (addresses == null || addresses.size == 0) {
            Toast.makeText(context, "주소 미발견", Toast.LENGTH_LONG).show()
            "주소 미발견"
        } else {
            val address = addresses[0]
            address.getAddressLine(0).toString()
        }
    }

    private fun showPlaceInformation(location: LatLng?) {
        mMap!!.clear() //지도 클리어
        goToMyLocation()
        if (previous_marker != null) previous_marker!!.clear() //지역정보 마커 클리어
        NRPlaces.Builder()
                .listener(this)
                .key("AIzaSyBIlMDJQLVxv6tNxqDyxAk5ErqPNm5Qmts")
                .latlng(location!!.latitude, location.longitude) //현재 위치
                .radius(radius) //500 미터 내에서 검색
                .type(PlaceType.CAFE) //카페
                .build()
                .execute()
    }

    override fun onPlacesFailure(e: PlacesException) {}
    override fun onPlacesStart() {}
    override fun onPlacesSuccess(places: List<Place>) {
        activity!!.runOnUiThread {
            for (place in places) {
                val latLng = LatLng(place.latitude, place.longitude)
                val markerSnippet = getAddress(latLng)
                val item = addMarkerInMap(latLng, place.name, markerSnippet, R.drawable.cafe_w_24_8995_ff)
                previous_marker!!.add(item)
            }

            //중복 마커 제거
            val hashSet = HashSet<Marker>()
            hashSet.addAll(previous_marker!!)
            previous_marker!!.clear()
            previous_marker!!.addAll(hashSet)
        }
    }

    override fun onPlacesFinished() {}
}