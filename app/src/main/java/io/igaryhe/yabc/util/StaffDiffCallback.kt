package io.igaryhe.yabc.util

import androidx.recyclerview.widget.DiffUtil
import io.igaryhe.yabc.models.Staff

class StaffDiffCallback : DiffUtil.ItemCallback<Staff>() {
    override fun areItemsTheSame(oldItem: Staff, newItem: Staff): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Staff, newItem: Staff): Boolean =
        oldItem == newItem
}