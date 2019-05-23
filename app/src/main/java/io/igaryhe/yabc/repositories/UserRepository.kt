package io.igaryhe.yabc.repositories

import android.app.Application
import android.content.Context
import io.igaryhe.yabc.api.BgmService

class UserRepository(app: Application) {
    private val sp = app.getSharedPreferences("token", Context.MODE_PRIVATE)
    private val userId = sp.getInt("user_id", 0)
    val user = BgmService.create().getUser(userId)
}