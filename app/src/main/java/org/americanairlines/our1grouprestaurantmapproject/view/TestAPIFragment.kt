package org.americanairlines.our1grouprestaurantmapproject.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import org.americanairlines.our1grouprestaurantmapproject.R

class TestAPIFragment : AppCompatActivity() {

    val apiKey = resources.getString(R.string.google_maps_key)


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


        // Initialize the SDK
        Places.initialize(applicationContext, apiKey)

        // Create a new PlacesClient instance
        val placesClient = Places.createClient(this)
        placesClient.)
    }
}