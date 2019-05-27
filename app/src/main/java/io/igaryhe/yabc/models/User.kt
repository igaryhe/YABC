package io.igaryhe.yabc.models

data class User(val username: String,
                val nickname: String,
                val id: Int,
                val usergroup: Int,
                val url: String,
                val sign: String,
                val avatar: Avatar)