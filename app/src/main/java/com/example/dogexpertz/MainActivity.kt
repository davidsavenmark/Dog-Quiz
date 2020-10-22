package com.example.dogexpertz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    //Denna funktion skapas automatiskt av Android när Class MainActivity är skapad.
    override fun onCreate(savedInstanceState: Bundle?) {

        // Gömmer status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // Kallar på parent constructorn
        super.onCreate(savedInstanceState)
        // Justerar xml viewn till denna class
        setContentView(R.layout.activity_main)

        // Villkor för när man trycker på start knappen: Om namnfältet står tomt så ska
        // " Please enter your name " stå kvar när man trycker på knappen.
        // Annars ska den hoppa in i nästa aktivitet
        btn_start.setOnClickListener () {
            if(et_name.text.toString().isEmpty()){
                Toast.makeText(this,
                    "Please enter your name", Toast.LENGTH_SHORT).show()

            }else{
                val intent = Intent(this,QuizQuestionsActivity::class.java)
                // STEG 2: Passerar namnet via intent där man använder en constant variabel som
                // har skapats.
                // START
                intent.putExtra(Constants.USER_NAME,et_name.text.toString())
                // END
                startActivity(intent)
                finish()

            }


        }


    }
}