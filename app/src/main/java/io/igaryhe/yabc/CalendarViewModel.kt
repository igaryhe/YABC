package io.igaryhe.yabc

import androidx.lifecycle.ViewModel

class CalendarViewModel : ViewModel() {
    val repo = CalendarRepository()
    val calendar = repo.calendar
}