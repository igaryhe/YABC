package io.igaryhe.yabc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.BgmLightTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        when (sp.getString("refresh_token", "")) {
            "" -> startActivity(Intent(this, LogInActivity::class.java))
            else -> startActivity(Intent(this, MainActivity::class.java))
        }
        finish()
    }
}
