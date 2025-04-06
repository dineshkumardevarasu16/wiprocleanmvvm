package com.observer.apiimplementation.domainLayer.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") @Expose val id: Int? = null,
    @SerializedName("title") @Expose val title: String? = null,
    @SerializedName("price") @Expose val price: Float? = null,
    @SerializedName("description") @Expose val description: String? = null,
    @SerializedName("category") @Expose val category: String? = null,
    @SerializedName("image") @Expose val image: String? = null,
    @SerializedName("rating") @Expose val rating: Rating? = null
)

data class Rating(
    @SerializedName("rate") @Expose val rate: Double? = null,
    @SerializedName("count") @Expose val count: Int? = null
)
