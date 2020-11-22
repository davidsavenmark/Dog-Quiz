package com.example.dogexpertz

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface QuestionDao {


    @Dao



    interface QuestionDao


    @Insert
    fun insert (question: Question)


    @Delete
    fun delete (question: Question)


    @Query("SELECT * FROM item")
    fun getAll() : List<Question>


    //@Query("SELECT * FROM item WHERE TOTAL_QUESTIONS LIKE :categoryName")
    //fun showCorrectAnswers(categoryName: String) : List <Question>



















}