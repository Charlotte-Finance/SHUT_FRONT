package com.example.shut_fe.models.user

data class PostUser(
    var id: Int? = null,
    var email: String? = null,
    var password: String? = null,
    var forename: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var address: String? = null,
    var city: String? = null,
    var country: String? = null,
    var gender: String? = null,
)