package io.igaryhe.yabc.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.igaryhe.yabc.viewModels.CalendarViewModel
import io.igaryhe.yabc.R
import io.igaryhe.yabc.adapters.SubjectSmallAdapter
import io.igaryhe.yabc.models.SubjectSmall
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.subject_card.view.*

class CalendarFragment(private val day: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = SubjectSmallAdapter()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mCalendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel::class.java)
        val adapter = cal_list.adapter!! as SubjectSmallAdapter
        mCalendarViewModel.calendar[day].observe(this, Observer<List<SubjectSmall>> {
            adapter.submitList(it)
            adapter.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as RecyclerView.ViewHolder
                val position = viewHolder.adapterPosition
                val id = it[position].id
                val action = DiscoverFragmentDirections.actionDiscoverToSubject(id, it[position].images.large)
                val extra = FragmentNavigatorExtras(viewHolder.itemView.subjectCover to "$id")
                view.findNavController().navigate(action, extra)
            }
        })
    }
}
