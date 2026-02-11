package com.yutahnahsyah.upsmartcanteenfrontend.data.model

data class Notification(
    val id: String,
    val title: String,
    val message: String,
    val time: String, // e.g., "5m ago", "1h ago", "Yesterday"
    val isRead: Boolean
)
