package io.igaryhe.yabc.repositories

import androidx.lifecycle.LiveData
import io.igaryhe.yabc.api.BgmService
import io.igaryhe.yabc.entities.SubjectMedium

class SubjectRepo (id: Int){
    val subjectMedium : LiveData<SubjectMedium> = BgmService.create().getMediumSubject(id)
}