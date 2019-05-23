package io.igaryhe.yabc

import com.google.gson.annotations.SerializedName

data class CollectionSubject (@SerializedName("ep_status") val epStatus: Int,
                              @SerializedName("subject_id") val subjectId: Int,
                              val name: String,
                              val subjectSmall: SubjectSmall)