package com.observer.apiimplementation.domainLayer.model.input

import com.google.gson.annotations.SerializedName

data class LoginData(@SerializedName("username") val username : String, @SerializedName("password") val password : String)
