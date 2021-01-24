package org.americanairlines.our1grouprestaurantmapproject.viewmodel

import androidx.lifecycle.ViewModel

class PlaceViewModel: ViewModel() {

//    val placeLiveData: MutableLiveData<List<PlaceResult>> = MutableLiveData()
//    private val placeRetrofit: PlaceRetrofit = PlaceRetrofit()
//    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

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