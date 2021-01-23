package org.americanairlines.our1grouprestaurantmapproject.model.data

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.Database
import android.arch.persistence.room.DatabaseConfiguration
import android.arch.persistence.room.InvalidationTracker
import android.arch.persistence.room.RoomDatabase
import org.americanairlines.our1grouprestaurantmapproject.model.PlaceResult

@Database(version = 1, entities = arrayOf(PlaceResult::class))
abstract class PlaceDatabase: RoomDatabase() {

    companion object{
        const val DATABASE_NAME = "nearby_places.db"
    }

    abstract fun getPlacesDAO(): PlaceResult

}