package io.igaryhe.yabc.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import io.igaryhe.yabc.entities.CollectionSubject
import io.igaryhe.yabc.repositories.CollectionRepository

class CollectionViewModel(app: Application, type: Int) : AndroidViewModel(app) {
    private val repo = CollectionRepository(app)
    val subjects: LiveData<List<CollectionSubject>> = Transformations.map(repo.subjects) {subjects ->
        val mList = mutableListOf<CollectionSubject>()
        for (subject: CollectionSubject in subjects) {
            if (subject.subject.type == type) mList.add(subject)
        }
        mList
    }
}