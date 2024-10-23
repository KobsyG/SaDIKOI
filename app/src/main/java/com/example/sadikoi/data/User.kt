package com.example.sadikoi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) //todo autoGenerate = true ?
    val id: Int = 0,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name") //todo tej name ?
    val lastName: String,

    val number: Int,

    val mail: String,

    val passion: String
)

data class NameTuple(
    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,
)

//todo @Ignore ?
//@Fts4 ou @Fts3 utiles ?
//languageId de @Fts ?
//indices ?
