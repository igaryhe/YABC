package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.R
import io.igaryhe.yabc.models.SubjectSmall
import io.igaryhe.yabc.util.SubjectSmallDiffCallback
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*

class CalendarSubjectAdapter:
    ListAdapter<SubjectSmall, CalendarSubjectAdapter.CalendarSubjectViewHolder>(SubjectSmallDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CalendarSubjectViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.subject_card, parent, false)
        return CalendarSubjectViewHolder(v)
    }

    override fun onBindViewHolder(holder: CalendarSubjectViewHolder, position: Int) {
        holder.apply {
            subjectName.text = getItem(position).name
            subjectId.text = getItem(position).id.toString()
            if (getItem(position).images != null)
                Picasso.get().load(getItem(position).images.medium).into(subjectCover)
        }
    }

    inner class CalendarSubjectViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer
}