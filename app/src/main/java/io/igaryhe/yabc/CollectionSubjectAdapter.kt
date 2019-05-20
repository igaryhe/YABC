package io.igaryhe.yabc

import android.util.Log
import android.util.Log.d
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CollectionSubjectAdapter(var subjects: List<CollectionSubject>) :
    RecyclerView.Adapter<CollectionSubjectViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionSubjectViewHolder {
        val v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_card, parent, false)
        return CollectionSubjectViewHolder(v)
    }

    override fun getItemCount() = subjects.size

    override fun onBindViewHolder(holder: CollectionSubjectViewHolder, position: Int) {
        holder.bind(subjects[position])
    }
}

class CollectionSubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val collectionSubject = itemView.findViewById<CardView>(R.id.collectionSubject)
    var subjectCover = itemView.findViewById<ImageView>(R.id.subjectCover)
    val subjectName = itemView.findViewById<TextView>(R.id.subjectName)
    val subjectId = itemView.findViewById<TextView>(R.id.subjectId)

    fun bind(subject: CollectionSubject) {
        subjectName.text = subject.subject.name
        subjectId.text = subject.subject.id.toString()
        Picasso.get().isLoggingEnabled = true
        Picasso.get().load(subject.subject.images.medium).into(subjectCover)
    }
}