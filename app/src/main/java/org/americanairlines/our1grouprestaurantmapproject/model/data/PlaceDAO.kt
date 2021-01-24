package org.americanairlines.our1grouprestaurantmapproject.model.data


import org.americanairlines.our1grouprestaurantmapproject.model.PlaceResult

//@Dao
interface PlaceDAO {

   // @Insert
    fun insertPlace(place: PlaceResult)

  //  @Delete
    fun deletePlace(place: PlaceResult)

 //   @Update
    fun updatePlace(place: PlaceResult)


}