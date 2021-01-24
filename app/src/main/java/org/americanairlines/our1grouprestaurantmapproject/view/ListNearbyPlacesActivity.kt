package org.americanairlines.our1grouprestaurantmapproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.Observer
import org.americanairlines.our1grouprestaurantmapproject.R
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult
import org.americanairlines.our1grouprestaurantmapproject.view.adapter.PlaceAdapter
import org.americanairlines.our1grouprestaurantmapproject.viewmodel.PlaceViewModel


class ListNearbyPlacesActivity : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nearby_places)


        placeRecyclerView = findViewById(R.id.recyclerview_list_nearby)
        placeRecyclerView.adapter = placeAdapter

//        viewModel.placeLiveData.observe(this, Observer {
//            placeAdapter.updatePlaceList(it as List<PlaceResult>)
//        })
    }// End of onCreate


}// End of ListNearbyPlacesActivity