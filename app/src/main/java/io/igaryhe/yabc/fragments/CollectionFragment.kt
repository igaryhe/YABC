package io.igaryhe.yabc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.igaryhe.yabc.entities.CollectionSubject
import io.igaryhe.yabc.R
import io.igaryhe.yabc.adapters.CalendarSubjectAdapter
import io.igaryhe.yabc.viewModels.SubjectViewModel
import io.igaryhe.yabc.adapters.CollectionSubjectAdapter
import kotlinx.android.synthetic.main.fragment_collection.*

class CollectionFragment(private val type: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collection, container, false)
        val mSubjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel::class.java)
        mSubjectViewModel.subjects.observe(this, Observer<List<CollectionSubject>> {
            t ->
            val mList = mutableListOf<CollectionSubject>()
            for (subject: CollectionSubject in t) {
                if (subject.subject.type == type) mList.add(subject)
            }
            val adapter = col_list.adapter!! as CollectionSubjectAdapter
            adapter.setCollectionSubjects(mList)
        })
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CollectionSubjectAdapter()
            }
        }
        return view
    }
}
