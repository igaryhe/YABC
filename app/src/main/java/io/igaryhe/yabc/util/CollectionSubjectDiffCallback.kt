package io.igaryhe.yabc.util

import androidx.recyclerview.widget.DiffUtil
import io.igaryhe.yabc.models.CollectionSubject

class CollectionSubjectDiffCallback : DiffUtil.ItemCallback<CollectionSubject>() {
    override fun areItemsTheSame(oldItem: CollectionSubject, newItem: CollectionSubject): Boolean =
        oldItem.subjectId == newItem.subjectId

    override fun areContentsTheSame(oldItem: CollectionSubject, newItem: CollectionSubject): Boolean =
        oldItem == newItem
}