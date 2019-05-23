package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.entities.CollectionSubject
import io.igaryhe.yabc.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*

class CollectionSubjectAdapter:
    RecyclerView.Adapter<CollectionSubjectAdapter.CollectionSubjectViewHolder>() {

    private var mSubjects = listOf<CollectionSubject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CollectionSubjectViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.subject_card, parent, false)
        return CollectionSubjectViewHolder(v)
    }
    override fun getItemCount() = mSubjects.size
    override fun onBindViewHolder(holder: CollectionSubjectViewHolder, position: Int) {
        holder.apply {
            subjectName.text = mSubjects[position].subject.name
            subjectId.text = mSubjects[position].subject.id.toString()
            Picasso.get().load(mSubjects[position].subject.images.large).resize(200, 284).into(subjectCover)
        }
    }

    fun setCollectionSubjects(subjects: List<CollectionSubject>) {
        mSubjects = subjects
        notifyDataSetChanged()
    }

    inner class CollectionSubjectViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer
}