package com.prarthana.mapapplication

import android.app.Activity
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker

class MyInfoWindowAdapter(private val context: Activity) : InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        val view = context.layoutInflater.inflate(R.layout.custominfowindow, null)
        val textViewTitle = view.findViewById<View>(R.id.text_title) as TextView
        val textViewSubTitle = view.findViewById<View>(R.id.text_subTitle) as TextView
        textViewTitle.text = marker.title
        textViewSubTitle.text = marker.snippet
        return view
    }
//
//    //set custom infowindow adapter
//    MyInfoWindowAdapter adapter = new MyInfoWindowAdapter(MapsActivity.this)
//    map.setInfoWindowAdapter(adapter)
//
//    map.addMarker(markerOpt).showInfoWindow();
}