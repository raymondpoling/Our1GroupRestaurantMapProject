package org.americanairlines.our1grouprestaurantmapproject.model.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update
import org.americanairlines.our1grouprestaurantmapproject.model.PlaceResult

@Dao
interface PlaceDAO {

    @Insert
    fun insertPlace(place: PlaceResult)

    @Delete
    fun deletePlace(place: PlaceResult)

    @Update
    fun updatePlace(place: PlaceResult)


}