package io.igaryhe.yabc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.R
import io.igaryhe.yabc.entities.SubjectSmall
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.subject_card.*

class CalendarSubjectAdapter(subjects: List<SubjectSmall>) :
    RecyclerView.Adapter<CalendarSubjectAdapter.CalendarSubjectViewHolder>() {
    private var mSubjects = subjects
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarSubjectViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.subject_card, parent, false)
        return CalendarSubjectViewHolder(v)
    }

    override fun getItemCount(): Int = mSubjects.size

    override fun onBindViewHolder(holder: CalendarSubjectViewHolder, position: Int) {
        holder.apply {
            subjectName.text = mSubjects[position].name
            subjectId.text = mSubjects[position].id.toString()
            // Log.d("dDEBUG", mSubjects[position].images.grid + " " + position)
            if (mSubjects[position].images != null)
                Picasso.get().load(mSubjects[position].images.medium).into(subjectCover)
        }
    }

    fun setCalendarSubjects(subjects: List<SubjectSmall>) {
        mSubjects = subjects
        notifyDataSetChanged()
    }

    inner class CalendarSubjectViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer
}