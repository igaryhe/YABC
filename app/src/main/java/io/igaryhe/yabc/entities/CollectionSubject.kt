package io.igaryhe.yabc.entities

import com.google.gson.annotations.SerializedName
import io.igaryhe.yabc.entities.SubjectSmall

data class CollectionSubject (@SerializedName("ep_status") val epStatus: Int,
                              @SerializedName("subject_id") val subjectId: Int,
                              val name: String,
                              val subject: SubjectSmall
)