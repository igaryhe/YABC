package io.igaryhe.yabc.viewModels

import androidx.lifecycle.ViewModel
import io.igaryhe.yabc.repositories.CalendarRepository

class CalendarViewModel : ViewModel() {
    val repo = CalendarRepository()
    val calendar = repo.calendar
}