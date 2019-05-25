package io.igaryhe.yabc.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.igaryhe.yabc.repositories.SubjectRepo

class SubjectMediumViewModel(app: Application) : AndroidViewModel(app){
    val repo = SubjectRepo(app)
    val subjectMedium = repo.subjectMedium
}