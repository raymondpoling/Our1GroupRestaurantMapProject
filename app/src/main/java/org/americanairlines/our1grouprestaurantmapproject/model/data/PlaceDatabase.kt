package org.americanairlines.our1grouprestaurantmapproject.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import org.americanairlines.our1grouprestaurantmapproject.model.PlaceResult

@Database(version = 1, entities = arrayOf(PlaceResult::class))
abstract class PlaceDatabase: RoomDatabase() {

    companion object{
        const val DATABASE_NAME = "nearby_places.db"
    }

    abstract fun getPlacesDAO(): PlaceResult

}