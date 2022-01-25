package com.example.shut_fe.models.preference


data class PostPreference(
    private var userId: Int,
    private var maxSound: String,
    private var maxVibration: String,
    private var soundControl: Boolean,
    private var colorAlert: Boolean,
    private var soundAlert: Boolean,
    private var ceil: String,
)