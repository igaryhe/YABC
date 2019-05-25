package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.entities.CollectionSubject
import io.igaryhe.yabc.R
import io.igaryhe.yabc.fragments.MainFragmentDirections
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
            val id = mSubjects[position].subject.id
            val image = mSubjects[position].subject.images.large
            subjectId.text = id.toString()
            Picasso.get().load(image).resize(200, 284).into(subjectCover)
            collectionSubject.setOnClickListener { v ->
                val action = MainFragmentDirections.actionMainToSubject(id, image)
                // val extra = FragmentNavigatorExtras(subjectCover to "cover")
                v.findNavController().navigate(action)
            }
        }
    }

    fun setCollectionSubjects(subjects: List<CollectionSubject>) {
        mSubjects = subjects
        notifyDataSetChanged()
    }

    inner class CollectionSubjectViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer
}