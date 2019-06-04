package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.igaryhe.yabc.databinding.CrtCardBinding
import io.igaryhe.yabc.models.Crt
import io.igaryhe.yabc.util.CrtDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*

class CrtCardAdapter:
    ListAdapter<Crt, CrtCardAdapter.ViewHolder>(CrtDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CrtCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: CrtCardBinding)
        : RecyclerView.ViewHolder(binding.root), LayoutContainer {
        override val containerView: View = binding.root

        fun bind(subject: Crt) {
            binding.crt = subject
            binding.executePendingBindings()
        }
    }
}