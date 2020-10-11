package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // print the name of fragments in the back stack
        val countStacks = fragmentManager?.backStackEntryCount
        for (i in 0 until countStacks!!) {
            Log.i(
                    MainActivity.TAG, "GameWonFragment backStack >>  "
                    + (fragmentManager?.getBackStackEntryAt(i)?.id) + "\n"
            )
        }
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener {
            it.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }


        setHasOptionsMenu(true)


        return binding.root
    }

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(context, "NumCorrect: ${args.numCorrect} --  NumQuestions: ${args.numQues}", Toast.LENGTH_LONG).show()

        return ShareCompat.IntentBuilder.from(requireActivity())
                .setText(getString(R.string.share_success_text, args.numQues, args.numCorrect))
                .setType("text/plain")
                .setChooserTitle("chose from those apps: ")
                .intent


    }

    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        // packageManager knows about every activity that is registered in the AndroidManifest
        // across every application so it can be used to see if our implicit intent
        // will resolve to something
        if (null == getShareIntent().resolveActivity(requireActivity().packageManager)) {
            menu.findItem(R.id.share).isVisible = false
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }


}
