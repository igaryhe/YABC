package io.igaryhe.yabc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.lifecycle.Observer

class SplashActivity : AppCompatActivity() {

    private val clientId = "bgm9385c7f6c28b6226"
    private val clientSecret = "08185eb0aae30419a40b5aedb304ef9c"
    private val redirectUri = "yabc://callback"

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.BgmLightTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        when (val refreshToken = sp.getString("refresh_token", "")) {
            "" -> startActivity(Intent(this, LogInActivity::class.java))
            else -> {
                val api = BgmService.create()
                api.refreshToken(clientId, clientSecret,
                    refreshToken!!, redirectUri, "refresh_token")
                    .observe(this, Observer<RefreshToken> {
                    t -> saveRefreshToken(t) })
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        finish()
    }
    private fun saveRefreshToken(result: RefreshToken) {
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("access_token", result.accessToken)
        editor.putString("refresh_token", result.refreshToken)
        editor.apply()
    }
}
