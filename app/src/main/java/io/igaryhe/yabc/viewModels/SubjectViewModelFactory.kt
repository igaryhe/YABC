package io.igaryhe.yabc.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SubjectViewModelFactory (private val id: Int):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SubjectMediumViewModel(id) as T
    }

}