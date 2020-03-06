package com.mosso.mainmenu.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mosso.mainmenu.R
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = findNavController(R.id.navigationHostFragment)
        navigationBottomView.setupWithNavController(navController)




       /* val navController = Navigation.findNavController(this, R.id.navigationHostFragment)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationBottomView)
        bottomNavigationView.let {
            NavigationUI.setupWithNavController(it, navController)
        }*/
    }
}