package io.igaryhe.yabc.entities

import com.google.gson.annotations.SerializedName

data class Collection(val wish: Int,
                      val collect: Int,
                      val doing: Int,
                      @SerializedName("on_hold") val onHold: Int,
                      val dropped: Int
)