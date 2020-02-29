package com.mosso.tracerouter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.mosso.mainmenu.presentation.MainMenuActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            goToMainMenu()
        }, TIME_OUT_SPLASH)
    }

    private fun goToMainMenu() {
        startActivity(Intent(this, MainMenuActivity::class.java))
        finish()
    }

    companion object {
        const val TIME_OUT_SPLASH = 4000L
    }
}
