package com.example.dogexpertz

import androidx.room.*

@Dao public abstract interface QuestionDao {

    @Dao
    interface QuestionDao

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(question: Question)


    @Delete
    fun delete(question: Question)


    @Query("SELECT * FROM Questions")
    fun getAll(): List<Question>


    @Query("SELECT * FROM Questions WHERE question LIKE :questionName")
    fun findByQuestion(questionName: String): List<Question>
}