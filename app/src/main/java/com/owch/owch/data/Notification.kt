package com.owch.owch.data

import java.util.Date

data class Notification(
    val id: Long,
    val title: String,
    val message: String,
    val date: Date
)
