package io.igaryhe.yabc.repositories

import io.igaryhe.yabc.api.BgmService
import android.app.Application

class SubjectRepo (app:Application){
    val subjectMedium = BgmService.create().getMediumSubject(123456)
}