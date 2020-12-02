package com.example.dogexpertz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.dogexpertz.R.*
import kotlinx.android.synthetic.main.activity_quiz_questions.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var db : QuizDataBase
    private var mCurrentPosition: Int = 1 // Default värde för frågepositionen.
    private var mQuestionsList: ArrayList<Question> = ArrayList() // Default värde för storleken på
                                                            // frågelistan
    private var mSelectedOptionPosition : Int = 0
    private var mCorrectAnswers: Int = 0


    // (STEG 3: Skapar en variabel för att hämta namnet från intent.
    // START
    private var mUserName: String? = null
    //END

    //Denna funktion skapas automatiskt av Android när Class QuizQuestionsActivity är skapad.
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN // Gömmer status bar.

        // Kallar på parent constructorn
        super.onCreate(savedInstanceState)
        // Används för att justera xml viewn till denna class
        setContentView(layout.activity_quiz_questions)
        db = QuizDataBase.invoke(this)
        CoroutineScope(Dispatchers.IO).async {
            db.questionDao().getAll().forEach {
                mQuestionsList.add(it)

            }
            setQuestion()
        }



        // (STEG 4: Hämtar NAME från intent och tilldelar det till en variabel
        // START
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        // END

        // Hämtar in funktionen getQuestions som sen läser in frågan och dom fyra
        // svars-alternativen 
        //mQuestionsList = Constants.getQuestions()



        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

        mCurrentPosition = 1
        //val question: Question? = mQuestionsList!![mCurrentPosition-1]
    }

    // Funktion som sätter frågan till UI komponenten.
    private fun setQuestion(){

        val question = mQuestionsList!![mCurrentPosition -1]//Hämtar frågan från listan via
                                                                    // mCurrentPosition

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "FINISH"
        }else{
            btn_submit.text = " SUBMIT"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        tv_question.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }
    // Funktion som sätter default options view när ny fråga har laddats in eller när svaret blir
    // omvalt.
    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,
                drawable.default_option_border_bg)
        }

    }

    // Läser in det svarsalternativ man har valt.
    override fun onClick(v: View?) {

        when(v?.id){
            R.id.tv_option_one ->{
                selectedOptionView(tv_option_one,1)

            }
            R.id.tv_option_two->{
                selectedOptionView(tv_option_two,2)
            }
            R.id.tv_option_three->{
                selectedOptionView(tv_option_three,3)
            }
            R.id.tv_option_four->{
                selectedOptionView(tv_option_four,4)
            }
            R.id.btn_submit ->{
                if(mSelectedOptionPosition == 0){
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }else ->{
                            //STEG 5: Tar bort toast meddelandet och kör resultatskärmen
                            //som vi har skapat som sen tar emot användarnamn och poängen.
                            //START
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                            //END

                        }
                    }
                }else{
                    // Läser in frågan via mQuestionsList? och mCurrentPosition
                    val question = mQuestionsList?.get(mCurrentPosition -1)

                    // Kollar om svaret är fel = Röd färg
                    if (question!!.correctAnswer !=mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    // Detta visar rätt svar = Grön färg
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if(mCurrentPosition == mQuestionsList!!.size){
                        btn_submit.text = "FINISHED"

                    }else{
                        btn_submit.text = "Next Question"

                    }
                    mSelectedOptionPosition = 0
                }
            }
        }

    }
    // Funktion för answerview som används till att visa om svaret är fel eller rätt.
    private fun answerView(answer: Int, drawableView: Int){
        when (answer){
            1 ->{
                tv_option_one.background = ContextCompat.getDrawable(
                    this, drawableView)

            }
            2 ->{
                tv_option_two.background = ContextCompat.getDrawable(
                    this, drawableView)

            }
            3 ->{
                tv_option_three.background = ContextCompat.getDrawable(
                    this, drawableView)

            }
            4 ->{
                tv_option_four.background = ContextCompat.getDrawable(
                    this, drawableView)

            }

        }

    }
    // Funktion som sätter viewn för selected option view.
    private fun selectedOptionView(tv:TextView, selectedOptionNumber: Int){

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,
            R.drawable.selected_option_border_bg)
    }
}