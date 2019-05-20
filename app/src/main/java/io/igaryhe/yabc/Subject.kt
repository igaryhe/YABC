package io.igaryhe.yabc

import com.google.gson.annotations.SerializedName

data class Subject(@SerializedName("eps_count") val epsCount: Int,
                   val images: Images,
                   val name: String,
                   @SerializedName("name_cn") val nameCN: String,
                   val type: Int,
                   val id: Int,
                   val summary: String)