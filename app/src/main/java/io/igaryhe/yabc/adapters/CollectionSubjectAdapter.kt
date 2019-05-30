package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.igaryhe.yabc.models.CollectionSubject
import io.igaryhe.yabc.util.CollectionSubjectDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*
import io.igaryhe.yabc.databinding.SubjectCardBinding
import io.igaryhe.yabc.models.SubjectSmall

class CollectionSubjectAdapter:
    ListAdapter<CollectionSubject, CollectionSubjectAdapter.ViewHolder>(CollectionSubjectDiffCallback()) {
    lateinit var onItemClickListener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubjectCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position).subject)
    }
    inner class ViewHolder(private val binding: SubjectCardBinding)
        : RecyclerView.ViewHolder(binding.root), LayoutContainer {
        override val containerView: View = binding.root
        init {
            collectionSubject.tag = this
            collectionSubject.setOnClickListener(onItemClickListener)
        }
        fun bind(subject: SubjectSmall) {
            binding.subject = subject
            binding.executePendingBindings()
        }
    }
}