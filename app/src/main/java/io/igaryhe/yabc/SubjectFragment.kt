package io.igaryhe.yabc

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_collection_subject.*

class SubjectFragment : Fragment() {

    private var subjects: List<CollectionSubject> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collection_subject, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CollectionSubjectAdapter(subjects)
            }
        }
        return view
    }

    fun setAdapter(subjects: List<CollectionSubject>) {
        this.subjects = subjects
        list.adapter!!.notifyDataSetChanged()
    }
}
