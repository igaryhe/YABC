package io.igaryhe.yabc.models

import com.google.gson.annotations.SerializedName

data class SubjectSmall(val id: Int,
                        val url: String,
                        val type: Int,
                        val name: String,
                        @SerializedName("name_cn") val nameCn: String,
                        val summary: String,
                        @SerializedName("air_date") val airDate: String,
                        @SerializedName("air_weekday") val airWeekday: Int,
                        val images: Images?
)