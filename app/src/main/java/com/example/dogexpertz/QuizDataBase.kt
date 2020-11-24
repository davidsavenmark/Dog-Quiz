package com.example.dogexpertz

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = arrayOf(Question::class), version = 2)
abstract class QuizDataBase : RoomDatabase() {

    abstract fun questionDao() : QuestionDao
}