package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.databinding.SubjectCardBinding
import io.igaryhe.yabc.models.SubjectSmall
import io.igaryhe.yabc.util.SubjectSmallDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*

class SubjectSmallAdapter:
    ListAdapter<SubjectSmall, SubjectSmallAdapter.ViewHolder>(SubjectSmallDiffCallback()){
    lateinit var onItemClickListener: View.OnClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SubjectCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
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