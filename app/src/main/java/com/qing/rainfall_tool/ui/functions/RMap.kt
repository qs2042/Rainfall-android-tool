package com.qing.rainfall_tool.ui.functions

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.qing.rainfall_tool.R
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.DefaultOverlayManager
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.TilesOverlay

class RMap : AppCompatActivity() {
    private lateinit var locationManager: LocationManager

    private lateinit var mapView: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_map)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        // 检查权限
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 已经具有位置权限
            requestLocation()
        } else {
            // 请求位置权限
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1001
//                LOCATION_PERMISSION_REQUEST_CODE
            )
        }


        mapView = findViewById<MapView>(R.id.v_map)

        Configuration.getInstance().userAgentValue =
            "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36 Edg/109.0.1518.140"

        // 设置地图图层
        /*
        DEFAULT_TILE_SOURCE 默认图层(基本的地图背景)
        MAPNIK              标准图层(道路、建筑物、河流等)
        CYCLEMAP            针对自行车和骑行者的图层
        PUBLIC_TRANSPORT    公共交通地图图层
        HIKING              徒步和远足者设计的地图图层
         */
//        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
        mapView.setTileSource(TileSourceFactory.MAPNIK)

        // 初始化地图相关配置
        mapView.setMultiTouchControls(true)

        // OpenStreetMap
        val mapController = mapView.controller
        val latitude = 37.7749 // 纬度值
        val longitude = -122.4194 // 经度值
        val startPoint = GeoPoint(latitude, longitude)
        mapController.setCenter(startPoint) // 设置地图中心点
        mapController.setZoom(12) // 设置缩放级别

        val marker = Marker(mapView)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)
    }

    private fun requestLocation() {
        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 没有位置权限
            Log.i("2042", "onLocationChanged: 没有位置权限")
            return
        }

        val locationListener = object : android.location.LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude
                Log.i("2042", "onLocationChanged: latitude($latitude), longitude($longitude)")
                // 在这里使用获取到的经纬度
                Toast.makeText(
                    this@RMap,
                    "latitude($latitude), longitude($longitude)",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null)
    }
}

