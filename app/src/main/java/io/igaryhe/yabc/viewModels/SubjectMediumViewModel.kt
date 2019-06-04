package io.igaryhe.yabc.viewModels

import android.provider.Telephony
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.igaryhe.yabc.models.Count
import io.igaryhe.yabc.models.Crt
import io.igaryhe.yabc.models.Rating
import io.igaryhe.yabc.models.Staff
import io.igaryhe.yabc.repositories.SubjectRepository

class SubjectMediumViewModel(private val _id: Int): ViewModel(){
    private val repo = SubjectRepository(_id)
    val id: LiveData<Int> = Transformations.map(repo.subjectMedium) {subject -> subject.id}
    val url: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.url}
    val name: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.name}
    val nameCn: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.nameCn}
    val summary: LiveData<String> = Transformations.map(repo.subjectMedium) {subject -> subject.summary}
    val score: LiveData<Float> = Transformations.map(repo.subjectMedium) {subject -> subject.rating.score}
    val count: LiveData<Count> = Transformations.map(repo.subjectMedium) {subject -> subject.rating.count}
    val rating: LiveData<Rating> = Transformations.map(repo.subjectMedium) {subject -> subject.rating}
    val rank: LiveData<Int> = Transformations.map(repo.subjectMedium) {subject -> subject.rank}
    val crt: LiveData<List<Crt>> = Transformations.map(repo.subjectMedium) {subject -> subject.crt}
    val staff: LiveData<List<Staff>> = Transformations.map(repo.subjectMedium) {subject -> subject.staff}
}