package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore:Int) : ViewModel() {


    private val _score = MutableLiveData<Int>()

    val score: LiveData<Int>
        get() = _score


    private val _eventPlayAgain = MutableLiveData<Boolean>()

    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        Log.i(this.javaClass.simpleName, "ScoreViewModel Created and" +
                " finalScore is : $finalScore")
        _score.value= finalScore
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(this.javaClass.simpleName, "ScoreViewModel Destroyed")

    }

    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }

    fun onPlayAgainComplete(){
        _eventPlayAgain.postValue(false)
    }

}