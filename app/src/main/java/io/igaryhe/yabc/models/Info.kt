package io.igaryhe.yabc.models

import com.google.gson.annotations.SerializedName

data class Info (@SerializedName("name_cn")
                 val nameCn: String,
                 val gender: String
)