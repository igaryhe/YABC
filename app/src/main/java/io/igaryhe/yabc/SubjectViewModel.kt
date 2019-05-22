package io.igaryhe.yabc

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SubjectViewModel(app: Application) : AndroidViewModel(app) {
    val repo = SubjectRepository(app)
    val subjects = repo.subjects
}