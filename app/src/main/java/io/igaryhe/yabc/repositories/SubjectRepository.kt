package io.igaryhe.yabc.repositories

import io.igaryhe.yabc.api.BgmService

class SubjectRepository (id: Int){
    val subjectMedium = BgmService.create().getMediumSubject(id)
}