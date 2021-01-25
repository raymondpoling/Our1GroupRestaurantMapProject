package org.americanairlines.our1grouprestaurantmapproject.view

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nearby_places)


        placeRecyclerView = findViewById(R.id.recyclerview_list_nearby)
        placeRecyclerView.adapter = placeAdapter

//        viewModel.placeLiveData.observe(this, Observer {
//            placeAdapter.updatePlaceList(it as List<PlaceResult>)
//        })
    }// End of onCreate

    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 2F, this)

        var myLoc: LatLng = LatLng(33.74,84.38)

        viewModel.getNearbyPlaces(myLoc)

    }

    override fun onLocationChanged(location: Location) {


    }

}// End of ListNearbyPlacesActivity