package io.igaryhe.yabc.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import io.igaryhe.yabc.entities.Calendar
import io.igaryhe.yabc.entities.SubjectSmall
import io.igaryhe.yabc.repositories.CalendarRepository

class CalendarViewModel : ViewModel() {
    val repo = CalendarRepository()
    val calendar = mutableListOf<LiveData<List<SubjectSmall>>>()
    init {
        for (i in 0..7) {
            calendar.add(Transformations.map(repo.calendar) {
                list -> list[i].items
            })
        }
    }
}