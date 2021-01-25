package org.americanairlines.our1grouprestaurantmapproject.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observer
import org.americanairlines.our1grouprestaurantmapproject.R
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.Geometry
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult
import org.americanairlines.our1grouprestaurantmapproject.view.adapter.PlaceAdapter
import org.americanairlines.our1grouprestaurantmapproject.viewmodel.PlaceViewModel


class ListNearbyPlacesActivity : AppCompatActivity(), LocationListener {

//TODO Homework Weekend: Due 24th 11:59pm - To be presented in training
//TODO Create an application that polls for the user location and lists all the nearby places in a recyclerView
//TODO - application should use Kotlin
//TODO - MVVM
//TODO - Should cache using Room Database for offline usage
//TODO - Application should use material design
//TODO - Runtime permissions
    /********************************************************************************/

    private val viewModel: PlaceViewModel by viewModels()
    private lateinit var placeRecyclerView: RecyclerView
    private val placeAdapter: PlaceAdapter = PlaceAdapter(mutableListOf())
    private lateinit var locationManager: LocationManager
    private val LOCATION_REQUEST_CODE = 707
    private lateinit var overlay: ConstraintLayout
    private lateinit var openSettingsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nearby_places)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        placeRecyclerView = findViewById(R.id.recyclerview_list_nearby)
        placeRecyclerView.adapter = placeAdapter
        overlay = findViewById(R.id.permission_overlay)
        openSettingsButton = findViewById(R.id.button_open_settings)
        openSettingsButton.setOnClickListener {
            // Implicit intent to open settings...this specific apps permissions
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, "Permissions")
            intent.data = uri
            startActivity(intent)
        }
//        viewModel.placeLiveData.observe(this, Observer {
//            placeAdapter.updatePlaceList(it as List<PlaceResult>)
//        })
    }// End of onCreate

    override fun onStart() {
        super.onStart()

        overlay.visibility = View.GONE
        checkLocationPermission()

//
//        var myLoc: LatLng = LatLng(33.74, 84.38)
//
//        viewModel.getNearbyPlaces(myLoc)

    }

    private fun checkLocationPermission() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            registerLocationManager()
        } else
            requestLocationPermission()
    }

    private fun requestLocationPermission() {

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_REQUEST_CODE
        )
    }

    @SuppressLint("MissingPermission")
    private fun registerLocationManager() {

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F, this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == LOCATION_REQUEST_CODE) {
            if (permissions[0] == android.Manifest.permission.ACCESS_FINE_LOCATION) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    ) {
                        requestLocationPermission()
                    } else {

                        overlay.visibility = View.VISIBLE
                    }
                }
            }
        }
    }// End onRequestPermissionsResult

    /***************************************************************************************/
    override fun onLocationChanged(location: Location) {

    }
}// End of ListNearbyPlacesActivity
