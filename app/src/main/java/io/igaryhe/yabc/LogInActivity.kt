package io.igaryhe.yabc

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LogInActivity : AppCompatActivity() {

    private val clientId = "bgm9385c7f6c28b6226"
    private val clientSecret = "08185eb0aae30419a40b5aedb304ef9c"
    private val redirectUri = "yabc://callback"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            val code = uri.getQueryParameter("code")
            val api = BgmOAuthService.create()
            val token = api.getAccessToken(clientId, clientSecret, code!!, redirectUri, "authorization_code")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result -> saveAccessToken(result)
                    Toast.makeText(this, result.accessToken, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()},
                    { error -> error.printStackTrace() })
        }
    }

    private fun saveAccessToken(result: AccessToken) {
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("access_token", result.accessToken)
        editor.putString("refresh_token", result.refreshToken)
        editor.putInt("user_id", result.userId)
        editor.apply()
    }

    fun onClick(v: View) {
        val uri = Uri.Builder().scheme("https")
            .authority("bgm.tv")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", clientId)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", redirectUri)
            .build()
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}