package io.igaryhe.yabc.viewModels

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.igaryhe.yabc.repositories.SearchRepository

class SearchViewModel(name: String) : ViewModel() {
    private val repo = SearchRepository(name)
    val subjects = Transformations.map(repo.result) {result -> result.list}
}