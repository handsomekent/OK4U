package my.edu.tarc.ok4umobile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class zMapsFragment : Fragment()//,GoogleMap.OnPoiClickListener
    , GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    OnRequestPermissionsResultCallback,
    OnMapReadyCallback
{

 //   lateinit var fusedLocation : FusedLocationProviderClient
    private var permissionDenied = false
    private lateinit var map: GoogleMap

//    private val PERTH = LatLng(-31.952854, 115.857342)
//    private val SYDNEY = LatLng(-33.87365, 151.20689)
//    private val BRISBANE = LatLng(-27.47093, 153.0235)
//
//    private lateinit var markerPerth: Marker
//    private lateinit var markerSydney: Marker
//    private lateinit var markerBrisbane: Marker


   // @SuppressLint("MissingPermission")
//    private val callback = OnMapReadyCallback { map ->
//
//        val database =
//            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
//                .getReference("Facilities")
//
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                for (i in snapshot.children) {
//                    var facilityName = i.child("name").getValue().toString()
//                    var lat = i.child("latitude").getValue().toString()
//                    var long = i.child("longitude").getValue().toString()
//                    var intLat: Double = lat.toDouble()
//                    var intLong: Double = long.toDouble()
//
//
//                    val temp1 = LatLng(intLat, intLong)
//
//                    map.addMarker(
//                        MarkerOptions()
//                            .position(temp1)
//                            .title(facilityName)
//                    )
//
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//        })
//
//
//        //val sydney = LatLng(-34.0, 151.0)
////        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
////        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f))
////        googleMap.setOnPoiClickListener(this)
//
////        markerPerth = map.addMarker(
////            MarkerOptions()
////                .position(PERTH)
////                .title("Perth"))
////        markerPerth.tag = 0
////        markerSydney = map.addMarker(
////            MarkerOptions()
////                .position(SYDNEY)
////                .title("Sydney")
////        )
////        markerSydney.tag = 0
////        markerBrisbane = map.addMarker(
////            MarkerOptions()
////                .position(BRISBANE)
////                .title("Brisbane")
////        )
////        markerBrisbane.tag = 0
//
//        // Set a listener for marker click.
//        map.setOnMarkerClickListener(this)
//     //   map.isMyLocationEnabled = true
//        map.setOnMyLocationButtonClickListener(this)
//        map.setOnMyLocationClickListener(this)
//        enableMyLocation()
//
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmentt_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//fusedLocation=LocationServices.getFusedLocationProviderClient(activity)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return

        val database =
            FirebaseDatabase.getInstance("https://ok4u-a1047-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("Facilities")

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (i in snapshot.children) {
                    var facilityName = i.child("facilityName").getValue().toString()
                    var lat = i.child("latitude").getValue().toString()
                    var long = i.child("longitude").getValue().toString()
                    var isFaci=i.child("facility").getValue().toString()
                    var intLat: Double = lat.toDouble()
                    var intLong: Double = long.toDouble()

                    val snippet=String.format(Locale.getDefault(),"Facility")
                    val temp1 = LatLng(intLat, intLong)
                    if(isFaci=="1"){
                        map.addMarker(
                            MarkerOptions()
                                .position(temp1)
                                .title(""+facilityName+" (Facility)")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))

                    }else{
                        map.addMarker(
                            MarkerOptions()
                                .position(temp1)
                                .title(""+facilityName+" (Service)")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
                    }


                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })





        googleMap.setOnMyLocationButtonClickListener(this)
        googleMap.setOnMyLocationClickListener(this)
        enableMyLocation()
    }

  //  @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (!::map.isInitialized) return
            if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ), LOCATION_PERMISSION_REQUEST_CODE
                )
            }
         else {
            // Permission to access the location is missing. Show rationale and request permission
                map.isMyLocationEnabled = true

        }
    }

    // set display data

    // get current location permission
    override fun onMyLocationClick(location: Location) {
        Toast.makeText(context, "Current location:\n$location", Toast.LENGTH_LONG)
            .show()
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(context, "MyLocation button clicked", Toast.LENGTH_SHORT)
            .show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
       // if (isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
        when(requestCode){
            requestCode->{
                if(grantResults.isEmpty()||grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    // Enable the my location layer if the permission has been granted.
                 //   permissionDenied = true
                     map.isMyLocationEnabled = false
                 //   map.isMyLocationEnabled = true

                } else {
                    // Permission was denied. Display an error message
                    // Display the missing permission error dialog when the fragments resume.
                  //  enableMyLocation()
                        map.isMyLocationEnabled = true
                //    map.isMyLocationEnabled = false
                }
            }
        }

    }

    fun onResumeFragments() {
        onResumeFragments()
        if (permissionDenied) {
            // Permission was not granted, display error dialog.

            permissionDenied = false
        }
    }



    companion object {
        /**
         * Request code for location permission request.
         *
         * @see .onRequestPermissionsResult
         */
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onMarkerClick(marker: Marker): Boolean {

        // Retrieve the data from the marker.
        val clickCount = marker.tag as? Int

        // Check if a click count was set, then display the click count.
        clickCount?.let {
            val newClickCount = it + 1
            marker.tag = newClickCount
            Toast.makeText(
                context,
                "${marker.title} has been clicked $newClickCount times.",
                Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }
//add on point
//    override fun onPoiClick(poi: PointOfInterest) {
//        var toast:Toast = Toast.makeText(
//            context,
//            """Clicked: ${poi.name}
//            Place ID:${poi.placeId}
//            Latitude:${poi.latLng.latitude} Longitude:${poi.latLng.longitude}""",
//            Toast.LENGTH_SHORT
//        )
//        toast.show()
//    }
}