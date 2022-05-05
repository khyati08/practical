package com.khyati.practical.model

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class CategoryListResponse(
    @JsonProperty("categories")
    var categories: ArrayList<Categories>? = null
)

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Categories(
    @JsonProperty("idCategory")
    var idCategory: String? = null,
    @JsonProperty("strCategory")
    var strCategory: String? = null,
    @JsonProperty("strCategoryThumb")
    var strCategoryThumb: String? = null,
    @JsonProperty("strCategoryDescription")
    var strCategoryDescription: String? = null,
)