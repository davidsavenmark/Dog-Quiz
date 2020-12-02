package com.example.dogexpertz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Question class

@Entity(tableName = "Questions")
data class Question(
    @ColumnInfo
    val question: String = "",
    @ColumnInfo
    val image: Int = 0,
    @ColumnInfo
    val optionOne: String = "",
    @ColumnInfo
    val optionTwo: String = "",
    @ColumnInfo
    val optionThree: String = "",
    @ColumnInfo
    val optionFour: String = "",
    @ColumnInfo
    val correctAnswer: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Int = 0,
)