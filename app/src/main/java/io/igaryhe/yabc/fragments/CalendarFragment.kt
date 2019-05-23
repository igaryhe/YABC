package io.igaryhe.yabc.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.igaryhe.yabc.viewModels.CalendarViewModel
import io.igaryhe.yabc.R
import io.igaryhe.yabc.adapters.CalendarSubjectAdapter
import io.igaryhe.yabc.entities.Calendar
import io.igaryhe.yabc.entities.SubjectSmall
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment(private val day: Int) : Fragment() {
    private var subjects: MutableList<SubjectSmall> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)

        val mCalendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel::class.java)
        mCalendarViewModel.calendar.observe(this, Observer<List<Calendar>> {
            t ->
            val adapter = cal_list.adapter!! as CalendarSubjectAdapter
            adapter.setCalendarSubjects(t[day].items)
        })
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CalendarSubjectAdapter(subjects)
            }
        }
        return view
    }
}
