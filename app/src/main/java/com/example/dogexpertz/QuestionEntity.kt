package com.example.dogexpertz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Item(@PrimaryKey(autoGenerate = true) val id: Int,
                @ColumnInfo(name = "user_name") val USER_NAME: String?,
                @ColumnInfo(name = "total_questions") val TOTAL_QUESTIONS : Int,
                @ColumnInfo(name = "total_answers") val TOTAL_ANSWERS : Int)


