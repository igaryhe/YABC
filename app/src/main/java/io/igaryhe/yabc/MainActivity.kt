package io.igaryhe.yabc

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        val userId = sp.getInt("user_id", 0)
        val api = BgmService.create()
        val collection = api.getUserCollection(userId, "watching")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({result ->
                val rv = findViewById<RecyclerView>(R.id.subjects)
                rv.layoutManager = LinearLayoutManager(this)
                rv.adapter = CollectionSubjectAdapter(result)
            }, { error -> error.printStackTrace() })
    }
}