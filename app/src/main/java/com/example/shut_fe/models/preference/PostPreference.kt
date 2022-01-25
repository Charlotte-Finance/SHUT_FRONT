package com.example.shut_fe.models.preference


data class PostPreference(
    var id: Int? = null,
    var userId: Int? = null,
    var maxSound: String? = null,
    var maxVibration: String? = null,
    var soundControl: Boolean? = null,
    var colorAlert: Boolean? = null,
    var soundAlert: Boolean? = null,
    var ceil: String? = null,
)