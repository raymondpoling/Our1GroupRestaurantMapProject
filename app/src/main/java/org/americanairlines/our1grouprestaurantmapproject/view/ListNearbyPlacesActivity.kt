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
<<<<<<< HEAD
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observer
import org.americanairlines.our1grouprestaurantmapproject.R
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.Geometry
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult
=======
import io.reactivex.Observer
import org.americanairlines.our1grouprestaurantmapproject.R
import org.americanairlines.our1grouprestaurantmapproject.model.NearbyPlacesModel
import org.americanairlines.our1grouprestaurantmapproject.util.DebugLogger.Companion.logger
>>>>>>> ef9e5a4ddeb13ae6c193a2d7488d04e51f607a3b
import org.americanairlines.our1grouprestaurantmapproject.view.adapter.PlaceAdapter
import org.americanairlines.our1grouprestaurantmapproject.viewmodel.PlaceViewModel


class ListNearbyPlacesActivity : AppCompatActivity(), LocationListener {

// Homework Weekend: Due 24th 11:59pm - To be presented in training
// Create an application that polls for the user location and lists all the nearby places in a recyclerView
// - application should use Kotlin
// - MVVM
// - Should cache using Room Database for offline usage
//- Application should use material design
// - Runtime permissions
    /********************************************************************************/

    private val viewModel: PlaceViewModel by viewModels()
    private lateinit var placeRecyclerView: RecyclerView
    private val placeAdapter: PlaceAdapter = PlaceAdapter(mutableListOf())
    private lateinit var locationManager: LocationManager
    private val LOCATION_REQUEST_CODE = 707
    private lateinit var overlay: ConstraintLayout
    private lateinit var openSettingsButton: Button

    companion object {
        private lateinit var context: Context
        fun getContext(): Context = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nearby_places)

        context = this

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

        }// setOnClickListener


    }


    override fun onStart() {
        super.onStart()

        overlay.visibility = View.GONE
        checkLocationPermission()
        viewModel.getSearchResults(0.0, 0.0)
            .observe(this, {
                logger("Logging information: $it")
                placeAdapter.updatePlaceList(it)
            })

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
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
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
            if (permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
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
        logger("We have location {${location.latitude},${location.longitude}}")
        viewModel.getSearchResults(location.latitude, location.longitude)
    }
}// End of ListNearbyPlacesActivity
