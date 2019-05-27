package io.igaryhe.yabc.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.igaryhe.yabc.repositories.SubjectRepository

class SubjectMediumViewModel(private val _id: Int): ViewModel(){
    private val repo = SubjectRepository(_id)
    val id: LiveData<Int> = Transformations.map(repo.subjectMedium) {subject -> subject.id}
    val url: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.url}
    val name: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.name}
    val nameCn: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.nameCn}
    val summary: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.summary}
}