package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.models.CollectionSubject
import io.igaryhe.yabc.R
import io.igaryhe.yabc.fragments.MainFragmentDirections
import io.igaryhe.yabc.util.CollectionSubjectDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*

class CollectionSubjectAdapter:
    ListAdapter<CollectionSubject, CollectionSubjectAdapter.CollectionSubjectViewHolder>(CollectionSubjectDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CollectionSubjectViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.subject_card, parent, false)
        return CollectionSubjectViewHolder(v)
    }
    override fun onBindViewHolder(holder: CollectionSubjectViewHolder, position: Int) {
        holder.apply {
            with (getItem(position).subject) {
                subjectName.text = name
                val id = id
                val image = images.large
                subjectId.text = id.toString()
                subjectCover.transitionName = "$id"
                Picasso.get().load(image).resize(200, 284).into(subjectCover)
                collectionSubject.setOnClickListener { v ->
                    val action = MainFragmentDirections.actionMainToSubject(id, image)
                    val extra = FragmentNavigatorExtras(subjectCover to "$id")
                    v.findNavController().navigate(action, extra)
                }
            }
        }
    }
    inner class CollectionSubjectViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer
}