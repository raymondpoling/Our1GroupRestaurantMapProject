package org.americanairlines.our1grouprestaurantmapproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.americanairlines.our1grouprestaurantmapproject.model.googleapi.PlaceResult
import org.americanairlines.our1grouprestaurantmapproject.network.PlaceRetrofit

class PlaceViewModel: ViewModel() {

//    val placeLiveData: MutableLiveData<List<PlaceResult>> = MutableLiveData()
//    private val placeRetrofit: PlaceRetrofit = org.americanairlines.our1grouprestaurantmapproject.network.PlaceRetrofit()
//    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
//
//    fun getSearchResults(searchQueryPlace: String){
//        compositeDisposable.add(
//            placeRetrofit.getSearchQuery("")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .map{
//
//                }
//                .subscribe({
//                    placeLiveData.postValue()
//                    compositeDisposable.clear()
//                })
//        )
//    }

    override fun onCleared() {
        super.onCleared()
    }
}