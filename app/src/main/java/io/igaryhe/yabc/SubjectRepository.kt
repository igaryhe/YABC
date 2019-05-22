package io.igaryhe.yabc

import android.app.Application
import android.content.Context

class SubjectRepository(app: Application) {
    val sp = app.getSharedPreferences("token", Context.MODE_PRIVATE)
    val userId = sp.getInt("user_id", 0)
    val subjects = BgmService.create().getUserCollection(userId, "watching")
}