package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.igaryhe.yabc.databinding.StaffCardBinding
import io.igaryhe.yabc.models.Staff
import io.igaryhe.yabc.util.StaffDiffCallback
import kotlinx.android.extensions.LayoutContainer

class StaffCardAdapter:
    ListAdapter<Staff, StaffCardAdapter.ViewHolder>(StaffDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StaffCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: StaffCardBinding)
        : RecyclerView.ViewHolder(binding.root), LayoutContainer {
        override val containerView: View = binding.root

        fun bind(subject: Staff) {
            binding.staff = subject
            binding.executePendingBindings()
        }
    }
}