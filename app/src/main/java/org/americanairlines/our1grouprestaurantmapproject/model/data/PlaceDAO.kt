package org.americanairlines.our1grouprestaurantmapproject.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult

@Dao
interface PlaceDAO {

    @Insert
    fun insertPlace(place: PlaceResult)

    @Delete
    fun deletePlace(place: PlaceResult)

    @Update
    fun updatePlace(place: PlaceResult)


}