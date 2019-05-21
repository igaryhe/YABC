package io.igaryhe.yabc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*

class CollectionSubjectAdapter(var subjects: List<CollectionSubject>) :
    RecyclerView.Adapter<CollectionSubjectAdapter.CollectionSubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CollectionSubjectViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.subject_card, parent, false)
        return CollectionSubjectViewHolder(v)
    }
    override fun getItemCount() = subjects.size
    override fun onBindViewHolder(holder: CollectionSubjectViewHolder, position: Int) {
        holder.apply {
            subjectName.text = subjects[position].subject.name
            subjectId.text = subjects[position].subject.id.toString()
            Picasso.get().load(subjects[position].subject.images.medium).into(subjectCover)
        }
    }

    inner class CollectionSubjectViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer
}