package com.khyati.practical.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class MealListResponse(
    @JsonProperty("meals")
    var meals: ArrayList<Meals>? = null
)

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Meals(
    @JsonProperty("strMeal")
    var strMeal: String? = null,
    @JsonProperty("strMealThumb")
    var strMealThumb: String? = null,
    @JsonProperty("idMeal")
    var idMeal: String? = null
)