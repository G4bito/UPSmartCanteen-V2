package com.yutahnahsyah.upsmartcanteenfrontend.data.model

data class Order(
    val orderId: String,
    val date: String,
    val status: String, // e.g., "Completed", "Pending", "Cancelled"
    val totalPrice: Double,
    val items: String // Summary of items
)
