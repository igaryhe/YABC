package io.igaryhe.yabc.models

import com.google.gson.annotations.SerializedName

data class CollectionSubject (@SerializedName("ep_status") val epStatus: Int,
                              @SerializedName("subject_id") val subjectId: Int,
                              val name: String,
                              val subject: SubjectSmall
)