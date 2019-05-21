package io.igaryhe.yabc

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
                val oauthApi = BgmOAuthService.create()
                val token = oauthApi.refreshToken(clientId, clientSecret, refreshToken!!, redirectUri, "refresh_token")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result -> saveRefreshToken(result) },
                        { error ->
                            d("error", "refresh error")
                            error.printStackTrace() })
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
