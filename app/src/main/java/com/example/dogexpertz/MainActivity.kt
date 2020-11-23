package com.example.dogexpertz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var db : QuizDataBase

    //Denna funktion skapas automatiskt av Android när Class MainActivity är skapad.
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Gömmer status bar.
        // Kallar på parent constructorn
        super.onCreate(savedInstanceState)
        // Justerar xml viewn till denna class
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(applicationContext, QuizDataBase::class.java, "quiz-question")
            .fallbackToDestructiveMigration()
            .build()




        // Start-Knapp
        // Om namnfältet står tomt och man trycker på start så
        // kommer det upp en påminnelse längre ner vid skärmen  " Please enter your name "

        // Om namnfältet har text i sig och man trycker på start så kommer man vidare till nästa
        // aktivitet.
        btn_start.setOnClickListener () {
            if(et_name.text.toString().isEmpty()){
                Toast.makeText(this,
                    "Please enter your name", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,QuizQuestionsActivity::class.java)

                // STEG 2: Passerar namnet via intent där man använder en constant variabel som
                // finns i Constants.
                // START
                intent.putExtra(Constants.USER_NAME,et_name.text.toString())
                // END
                startActivity(intent)
                finish()

            }


        }


    }
}