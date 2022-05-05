package com.khyati.practical.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khyati.practical.model.CategoryListResponse
import com.khyati.practical.network.ApiClient
import com.khyati.practical.network.ResponseHandler
import com.khyati.practical.network.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/** Post screen view model */
class FirstViewModel : ViewModel() {
    var responseLiveCategoryResponse: MutableLiveData<ResponseHandler<CategoryListResponse?>>? =
        null

    init {
        responseLiveCategoryResponse = MutableLiveData()
        getCategories()
    }

    /** Call verify token api */
    private fun getCategories() {
        viewModelScope.launch(Dispatchers.Main + SupervisorJob()) {
            responseLiveCategoryResponse?.value = ResponseHandler.Loading
            val responseFromApi =
                UserRepository(ApiClient.getApiInterface()).getCategories()
            responseLiveCategoryResponse?.value = responseFromApi
        }
    }

}