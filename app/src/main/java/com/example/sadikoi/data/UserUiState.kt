package com.example.sadikoi.data

data class UserUiState(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
//    val number: Int = 0, //todo number string direct ?
    val number: String = "",
    val mail: String = "",
    val passion: String = "",
    val photoPath: String = "",
)
