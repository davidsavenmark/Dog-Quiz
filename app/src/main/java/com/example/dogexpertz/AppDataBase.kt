package com.example.dogexpertz

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Question::class), version = 1)
abstract class AppDataBase : RoomDatabase () {
    abstract fun QuestionDao() : QuestionDao
}