package com.example.android.guesstheword

/* in gameFragment
       // old code Not Needed any more thanks to LiveData  binding
        binding.correctButton.setOnClickListener {
            gameViewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            gameViewModel.onSkip()
        }

        gameViewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = newScore.toString()
        })

        gameViewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            binding.wordText.text = newWord
            gameViewModel.onGameFinishComplete()
        })

        gameViewModel.currentTime.observe(viewLifecycleOwner, Observer { newTime ->
            binding.timerText.text =  DateUtils.formatElapsedTime(newTime)
        })

 /** old code Methods for updating the UI **/
    /* private fun updateWordText() {
         binding.wordText.text = gameViewModel.word.value
     }

     private fun updateScoreText() {
         binding.scoreText.text = gameViewModel.score.toString()
     }*/
 */

/* in scoreFragment
        /*viewModel.score.observe(viewLifecycleOwner, Observer { finalScore ->
            binding.scoreText.text = finalScore.toString()
        })*/

     //   binding.playAgainButton.setOnClickListener { viewModel.onPlayAgain() }
 */