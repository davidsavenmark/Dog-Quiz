package com.example.dogexpertz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Question class med alla nödvändiga konstanta variabler


@Entity
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "question")val question: String,
    @ColumnInfo(name = "image")val image: Int,
    @ColumnInfo(name = "optionOne")val optionOne: String,
    @ColumnInfo(name = "optionTwo")val optionTwo: String,
    @ColumnInfo(name = "optionThree")val optionThree: String,
    @ColumnInfo(name = "OptionFour")val optionFour: String,
    @ColumnInfo(name = "correctAnswer")val correctAnswer: Int,)