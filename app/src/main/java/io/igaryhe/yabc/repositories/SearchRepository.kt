package io.igaryhe.yabc.repositories

import io.igaryhe.yabc.api.BgmService

class SearchRepository(name: String) {
    val result = BgmService.create().searchSubject(name)
}