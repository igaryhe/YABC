package io.igaryhe.yabc.util

import androidx.recyclerview.widget.DiffUtil
import io.igaryhe.yabc.models.Crt

class CrtDiffCallback : DiffUtil.ItemCallback<Crt>() {
    override fun areItemsTheSame(oldItem: Crt, newItem: Crt): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Crt, newItem: Crt): Boolean =
        oldItem == newItem
}