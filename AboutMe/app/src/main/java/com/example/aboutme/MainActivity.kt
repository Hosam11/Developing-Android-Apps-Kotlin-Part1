package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager


import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName = MyName("Hossam Elsayed")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  BEFORE Data Binding
        // setContentView(R.layout.activity_main)

        // create binding object that connect layout with activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.myName = myName

        /*  BEFORE Data Binding
            findViewById<Button>(R.id.done_btn).setOnClickListener{ addNickname(it) }
        */
        binding.doneBtn.setOnClickListener {
            addNickname(it)
        }
    }

    /**
     * view: the view that user clicked on
     */
    private fun addNickname(view: View) {

        /*  BEFORE Data Binding
          val edTxt = findViewById<EditText>(R.id.nickname_edit)
           val nicknameTxtView = findViewById<TextView>(R.id.nickname_text)

           nicknameTxtView.text = edTxt.text
           edTxt.visibility = View.GONE
           view.visibility = View.GONE
           nicknameTxtView.visibility = View.VISIBLE
           */
        binding.apply {
           // nicknameTv.text = nicknameEt.text
            myName?.nickname = nicknameEt.text.toString()
            // refresh the ui with new data - recreated with correct data
            invalidateAll()
            nicknameEt.visibility = View.GONE
            view.visibility = View.GONE
            nicknameTv.visibility = View.VISIBLE

        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}