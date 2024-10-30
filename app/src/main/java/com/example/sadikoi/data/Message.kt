package com.example.sadikoi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity( //todo d'apres chatgpt, est qu'on veut le cascade ondelete
//    tableName = "messages",
//    foreignKeys = [
//        ForeignKey(
//            entity = Contact::class,
//            parentColumns = ["contactId"],
//            childColumns = ["contactOwnerId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ]
//)

@Entity
data class Message (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val contactId: Int,

    val messageText: String,

    val timestamp: Long,

    val isSent: Boolean //todo ah ouai ?


)


