package io.igaryhe.yabc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.igaryhe.yabc.models.CollectionSubject
import io.igaryhe.yabc.R
import io.igaryhe.yabc.viewModels.CollectionViewModel
import io.igaryhe.yabc.adapters.CollectionSubjectAdapter
import io.igaryhe.yabc.util.CollectionViewModelFactory
import kotlinx.android.synthetic.main.fragment_collection.*

class CollectionFragment(private val type: Int) : Fragment() {
    private var v: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collection, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CollectionSubjectAdapter()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mCollectionViewModel = ViewModelProviders
            .of(this, CollectionViewModelFactory(activity!!.application, type))
            .get(CollectionViewModel::class.java)
        val adapter = col_list.adapter!! as CollectionSubjectAdapter
        mCollectionViewModel.subjects.observe(this, Observer<List<CollectionSubject>> {
            adapter.submitList(it)
        })
    }
}
