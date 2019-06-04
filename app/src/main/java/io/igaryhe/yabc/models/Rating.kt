package io.igaryhe.yabc.models

data class Rating(val total: Int,
                  val count: Count,
                  val score: Float){
    override fun toString(): String {
        return "$score / $total votes / rank: "
    }
}