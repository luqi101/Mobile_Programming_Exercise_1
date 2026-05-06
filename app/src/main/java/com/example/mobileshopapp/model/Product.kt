package com.example.mobileshopapp.model

import androidx.annotation.DrawableRes

data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val description: String,
    val priceCents: Int,
    @param:DrawableRes val imageResId: Int,
    val accentColor: Long
)
