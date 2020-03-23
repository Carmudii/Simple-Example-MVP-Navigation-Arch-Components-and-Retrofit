package com.example.mvpproject.models

import com.google.gson.annotations.SerializedName

data class AuthResponse(var status: Boolean, var message: String, var token: String = "")