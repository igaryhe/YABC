package io.igaryhe.yabc.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.igaryhe.yabc.repositories.CollectionRepository

class CollectionViewModel(app: Application) : AndroidViewModel(app) {
    val repo = CollectionRepository(app)
    val subjects = repo.subjects
}