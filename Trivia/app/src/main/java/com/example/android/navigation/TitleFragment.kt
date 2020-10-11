package com.example.android.navigation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // print the name of fragments in the back stack
        val countStacks = fragmentManager?.backStackEntryCount
        for (i in 0 until countStacks!!) {
            Log.i(
                    MainActivity.TAG, "TitleFragment backStack >>  "
                    + (fragmentManager?.getBackStackEntryAt(i)?.id) + "\n"
            )
        }
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)
        // First possible way to start navigate to game fragment
        /* binding.playButton.setOnClickListener { v: View ->
             Navigation.findNavController(v).navigate(R.id.action_titleFragment_to_gameFragment)
         }*/
        //  Second possible way to start navigate to game fragment
        /* Android KTX  has an extension function for android class view
            binding.playButton.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
         }*/
        // Third way - Navigation create onClickListener for us
        binding.playButton.setOnClickListener{
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }
}