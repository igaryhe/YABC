package io.igaryhe.yabc.viewModels

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.repositories.UserRepository

class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = UserRepository(app)
    val nickname: LiveData<String> = Transformations.map(repo.user) { user -> user.nickname }
    val username: LiveData<String> = Transformations.map(repo.user) { user -> user.username }
    val avatar: LiveData<String> = Transformations.map(repo.user) {user -> user.avatar.large}
}