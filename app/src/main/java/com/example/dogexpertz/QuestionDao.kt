package com.example.dogexpertz

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao public abstract interface QuestionDao {


    @Dao

    interface QuestionDao


    @Insert
    fun insert (question: Question)


    @Delete
    fun delete (question: Question)


    @Query("SELECT * FROM question")
    fun getAll() : List<Question>


    @Query("SELECT * FROM Question WHERE question LIKE :questionName")
    fun findByQuestion(questionName: String) : List <Question>


}