package com.khyati.practical.network

import com.khyati.practical.model.CategoryListResponse
import com.khyati.practical.model.MealListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val apiInterface: ApiInterface) : BaseRepository() {
    suspend fun getCategories(): ResponseHandler<CategoryListResponse?> {
        return withContext(Dispatchers.IO) {
            return@withContext makeAPICall {
                apiInterface.getCategories()
            }
        }
    }

    suspend fun getMealFromCategory(categoryName: String): ResponseHandler<MealListResponse?> {
        return withContext(Dispatchers.IO) {
            return@withContext makeAPICall {
                apiInterface.getMealFromCategory(categoryName)
            }
        }
    }
}