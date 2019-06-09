package io.igaryhe.yabc.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.lifecycle.Observer
import io.igaryhe.yabc.models.AccessToken
import io.igaryhe.yabc.api.BgmService
import io.igaryhe.yabc.R

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
            val api = BgmService.create()
            api.getAccessToken(clientId, clientSecret,
                code!!, redirectUri, "authorization_code")
                .observe(this, Observer<AccessToken> {
                    t ->
                    saveAccessToken(t)
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                })
        }
    }

    private fun saveAccessToken(result: AccessToken) {
        getSharedPreferences("token", Context.MODE_PRIVATE).edit {
            putString("access_token", result.accessToken)
            putString("refresh_token", result.refreshToken)
            putInt("user_id", result.userId)
        }
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