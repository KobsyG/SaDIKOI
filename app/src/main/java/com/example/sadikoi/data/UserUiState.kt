package com.example.sadikoi.data

data class UserUiState(
    val firstName: String = "",
    val lastName: String = "",
    val number: Int = 0, //todo number string direct ?
    val mail: String = "",
    val passion: String = ""
)
