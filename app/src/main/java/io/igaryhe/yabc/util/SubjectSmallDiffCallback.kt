package io.igaryhe.yabc.util

import androidx.recyclerview.widget.DiffUtil
import io.igaryhe.yabc.models.SubjectSmall

class SubjectSmallDiffCallback : DiffUtil.ItemCallback<SubjectSmall>() {
    override fun areItemsTheSame(oldItem: SubjectSmall, newItem: SubjectSmall): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SubjectSmall, newItem: SubjectSmall): Boolean =
        oldItem == newItem
}