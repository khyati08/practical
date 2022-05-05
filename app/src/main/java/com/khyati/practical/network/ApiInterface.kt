package com.khyati.practical.network

import com.khyati.practical.model.CategoryListResponse
import com.khyati.practical.model.MealListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


/** Interface used for api */
interface ApiInterface {

    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryListResponse>

    @GET("filter.php")
    suspend fun getMealFromCategory(@Query("c") categoryName: String): Response<MealListResponse>
}
