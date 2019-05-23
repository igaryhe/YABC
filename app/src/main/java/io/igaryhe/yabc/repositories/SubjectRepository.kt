package io.igaryhe.yabc.repositories

import android.app.Application
import android.content.Context
import io.igaryhe.yabc.api.BgmService

class SubjectRepository(app: Application) {
    val sp = app.getSharedPreferences("token", Context.MODE_PRIVATE)
    val userId = sp.getInt("user_id", 0)
    val subjects = BgmService.create().getUserCollection(userId, "watching_all")
}