package com.mosso.mainmenu.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mosso.mainmenu.R

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val navView: BottomNavigationView = findViewById(R.id.navigationBottomView)
        val navController = findNavController(R.id.navigationHostFragment)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.locationNavigation, R.id.routeNavigation))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}