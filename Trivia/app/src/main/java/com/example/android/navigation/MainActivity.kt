package com.example.android.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfig: AppBarConfiguration

    companion object {
        const val TAG = "nav_back_Stack"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")


        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawer
        /*  -info for the up button
            the nav controller has UI library called navigation UI it's integrate with action bar
            to implement the correct behavior for the up button so we don't have do it ourselves
            so we need to find the nav controller so the NavigationUI can access it
         */
        val navController = this.findNavController(R.id.navHostFragment)
        // Hock up NavigationUI to the ActionBar.
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        //  connect the DrawerLayout to your navigation graph by passing it to AppBarConfiguration
        // appBarConfig = AppBarConfiguration(navController.graph, drawerLayout)
        // Hook up the navigation UI up to the navigation view. so you can navigate
        NavigationUI.setupWithNavController(binding.navView, navController)
        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }

    }

    /*
    this method where we need to make navigation handle what happens when up button is pressed
    otherwise nothing will happen when we press up button
    */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        // {old code}
//         return navController.navigateUp()
        // so the NavigationUI can replace the up button with the navigation drawer button when we get to start destination
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
