package com.ikumb.edugate.db

data class User(
    var name: String? = "",
    var surname: String? = "",
    var birtday: String? = "",
    var department: String? = "",
    var isStudent: Boolean? = true
)