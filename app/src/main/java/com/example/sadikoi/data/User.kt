package com.example.sadikoi.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) //todo autoGenerate = true ?
    val id: Int = 0,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name") //todo tej name ?
    val lastName: String,

    val number: String, //todo String ou Int ?

    val mail: String,

    val passion: String,

    val photoPath: String? = null
)

//data class NameTuple( //todo c'est quoi deja cette merde
//    @ColumnInfo(name = "first_name")
//    val firstName: String,
//
//    @ColumnInfo(name = "last_name")
//    val lastName: String,
//)

data class UserWithMessages(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "contactId"
    )
    val messages: List<Message>
)

//todo @Ignore ?
//@Fts4 ou @Fts3 utiles ?
//languageId de @Fts ?
//indices ?
