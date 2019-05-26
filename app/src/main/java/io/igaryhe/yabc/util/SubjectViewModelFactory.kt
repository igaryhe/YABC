package io.igaryhe.yabc.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.igaryhe.yabc.viewModels.SubjectMediumViewModel

class SubjectViewModelFactory (private val id: Int):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SubjectMediumViewModel(id) as T
    }

}