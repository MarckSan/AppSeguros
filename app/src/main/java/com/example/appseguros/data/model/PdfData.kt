package com.example.appseguros.data.model

import androidx.annotation.DrawableRes

data class PdfData(
    val url: String,
    val name: String,
    @DrawableRes val icon: Int = 0
)