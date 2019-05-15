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

class MainActivity : AppCompatActivity() {

    private val clientId = "bgm9385c7f6c28b6226"
    private val clientSecret = "08185eb0aae30419a40b5aedb304ef9c"
    private val redirectUri = "yabc://callback"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        val refreshToken = sp.getString("refresh_token", "")
        if (refreshToken!! == "") {
            val intent = Intent(this, LogInActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        } else {
            Log.d("TOKEN", refreshToken)
            val api = BgmOAuthService.create()
            api.refreshToken(clientId, clientSecret, refreshToken, redirectUri, "refresh_token")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({result -> saveRefreshToken(result)
                    Toast.makeText(this, result.accessToken, Toast.LENGTH_SHORT).show()},
                    { error ->
                        Toast.makeText(this, "ERROR!", Toast.LENGTH_SHORT).show()
                        error.printStackTrace() })
        }
    }

    private fun saveRefreshToken(result: RefreshToken) {
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("access_token", result.accessToken)
        editor.putString("refresh_token", result.refreshToken)
        editor.apply()
    }
}