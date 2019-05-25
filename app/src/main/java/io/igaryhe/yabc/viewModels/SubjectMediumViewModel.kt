package io.igaryhe.yabc.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import io.igaryhe.yabc.repositories.SubjectRepo

class SubjectMediumViewModel(id: Int): ViewModel(){
    val repo = SubjectRepo(id)
    val subjectMedium = repo.subjectMedium
}