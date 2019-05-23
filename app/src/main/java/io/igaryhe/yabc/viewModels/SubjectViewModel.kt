package io.igaryhe.yabc.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.igaryhe.yabc.repositories.SubjectRepository

class SubjectViewModel(app: Application) : AndroidViewModel(app) {
    val repo = SubjectRepository(app)
    val subjects = repo.subjects
}