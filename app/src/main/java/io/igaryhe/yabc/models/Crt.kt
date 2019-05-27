package io.igaryhe.yabc.models

import com.google.gson.annotations.SerializedName

data class Crt (val id: Long = 0,
                val url: String,
                val name: String,
                @SerializedName("name_cn")
                val nameCn: String,
                @SerializedName("role_name")
                val roleName: String,
                val images: Images,
                val comment: Int,
                val collects: Int,
                val info: Info,
                val actors: List<Actor>
)