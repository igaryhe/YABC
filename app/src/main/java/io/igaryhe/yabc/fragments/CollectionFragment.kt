package io.igaryhe.yabc.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import io.igaryhe.yabc.models.CollectionSubject
import io.igaryhe.yabc.R
import io.igaryhe.yabc.viewModels.CollectionViewModel
import io.igaryhe.yabc.adapters.CollectionSubjectAdapter
import io.igaryhe.yabc.util.CollectionViewModelFactory
import kotlinx.android.synthetic.main.fragment_collection.*
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.subject_card.*
import kotlinx.android.synthetic.main.subject_card.view.*


class CollectionFragment(private val type: Int) : Fragment() {
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
            adapter.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as RecyclerView.ViewHolder
                val position = viewHolder.adapterPosition
                val id = it[position].subject.id
                val action = MainFragmentDirections.actionMainToSubject(id, it[position].subject.images.large)
                val extra = FragmentNavigatorExtras(viewHolder.itemView.subjectCover to "$id")
                view.findNavController().navigate(action, extra)
            }
        })
    }
}