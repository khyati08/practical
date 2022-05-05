package com.khyati.practical.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khyati.practical.model.MealListResponse
import com.khyati.practical.network.ApiClient
import com.khyati.practical.network.ResponseHandler
import com.khyati.practical.network.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/** Post screen view model */
class SecondViewModel : ViewModel() {
    var responseLiveMealResponse: MutableLiveData<ResponseHandler<MealListResponse?>>? = null

    init {
        responseLiveMealResponse = MutableLiveData()
    }

    /** Call verify token api */
    fun getMealFromCategory(categoryName: String) {
        viewModelScope.launch(Dispatchers.Main + SupervisorJob()) {
            responseLiveMealResponse?.value = ResponseHandler.Loading
            val responseFromApi =
                UserRepository(ApiClient.getApiInterface()).getMealFromCategory(categoryName)
            responseLiveMealResponse?.value = responseFromApi
        }
    }

}