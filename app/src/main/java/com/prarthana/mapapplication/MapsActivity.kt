package com.prarthana.mapapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(),
        GoogleMap.OnMarkerClickListener,OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        // Add a marker in Sydney and move the camera
         val m1 = LatLng(20.0110,73.7964)
         val m2 = LatLng(19.99, 73.7825)
         val m3 = LatLng(19.96,73.7584)
         val m4 = LatLng(19.9412,73.7486)
         val m5 = LatLng(19.9360,73.7420)
         val m6 = LatLng(19.9345,73.7412)
         val m7 = LatLng(19.9325,73.7405)

         lateinit var markerOne: Marker
         lateinit var markerTwo: Marker


// Move the camera instantly  with a zoom of 15.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m1, 15f))

// Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn())

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10f), 2000, null)

// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        val cameraPosition = CameraPosition.Builder()
                .target(m1) // Sets the center of the map to Mountain View
                .zoom(17f)            // Sets the zoom
                .bearing(90f)         // Sets the orientation of the camera to east
                .tilt(30f)            // Sets the tilt of the camera to 30 degrees
                .build()              // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        // Add some markers to the map, and add a data object to each marker.
        markerOne = googleMap.addMarker(
                MarkerOptions()
                        .position(m1)
                        .title("Nimani Bus Stand")
                        .snippet("Panchavati")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.googleg_standard_color_18))
        )

        markerTwo = googleMap.addMarker(
                MarkerOptions()
                        .position(m4)
                        .title("Pandav Leni")
                        .snippet("Historical landmark in Nashik, Maharashtra")
        )
        mMap.setInfoWindowAdapter(MyInfoWindowAdapter(this))


        mMap.addPolyline(
                PolylineOptions()
                        .add(m1)
                        .add(m2)
                        .add(m3)
                        .add(m4)
                        .width(8f)
                        .color(Color.DKGRAY)

        )

        mMap.addCircle(
                CircleOptions()
                        .center(m1)
                        .radius(500.0)
                        .strokeWidth(3f)
                        .strokeColor(Color.BLUE)
                        .fillColor(Color.argb(70,50,50,150))
        )

        mMap.addPolygon(
                PolygonOptions()
                        .add(m4)
                        .add(m5)
                        .add(m6)
                        .add(m7)
                        .fillColor(Color.CYAN)
                        .strokeColor(Color.RED)
        )
//
//        mMap.addGroundOverlay(
//                GroundOverlayOptions()
//                        .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_foreground))
//                        .position(m4,8600f,6500f)
//        )
        mMap.setOnInfoWindowClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(m1));
        // Set a listener for marker click.
        googleMap.setOnMarkerClickListener(this)


    }
    /** Called when the user clicks a marker.  */
    override fun onMarkerClick(marker: Marker): Boolean {

        // Retrieve the data from the marker.
        val clickCount = marker.tag as? Int

        // Check if a click count was set, then display the click count.
        clickCount?.let {
            val newClickCount = it + 1
            marker.tag = newClickCount
            Toast.makeText(
                    this,
                    "${marker.title} has been clicked $newClickCount times.",
                    Toast.LENGTH_SHORT
            ).show()
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false
    }

    override fun onInfoWindowClick(p0: Marker?) {
        Toast.makeText(this,"The Nasik Caves, or sometimes Pandavleni Caves, are a group of 23 caves carved between the 1st century BCE and the 3rd century CE, though additional sculptures were added up to about the 6th century, reflecting changes in Buddhist devotional practices mainly.",Toast.LENGTH_LONG).show();
    }

//    override fun onMapReady(googleMap: GoogleMap){
//        mMap = googleMap
//        mMap.setInfoWindowAdapter(MyInfoWindowAdapter(this))
//    }
}