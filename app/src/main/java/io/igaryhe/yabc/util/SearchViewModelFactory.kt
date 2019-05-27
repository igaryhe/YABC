package io.igaryhe.yabc.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.igaryhe.yabc.viewModels.SearchViewModel

class SearchViewModelFactory(private val name: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchViewModel(name) as T
}