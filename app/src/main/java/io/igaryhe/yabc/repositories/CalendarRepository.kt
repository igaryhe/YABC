package io.igaryhe.yabc.repositories

import io.igaryhe.yabc.api.BgmService

class CalendarRepository() {
    val calendar = BgmService.create().getCalendar()
}