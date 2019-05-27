package io.igaryhe.yabc.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.igaryhe.yabc.viewModels.CollectionViewModel
import io.igaryhe.yabc.viewModels.SubjectMediumViewModel

class CollectionViewModelFactory(private val app: Application, private val type: Int) :
    ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = CollectionViewModel(app, type) as T
}