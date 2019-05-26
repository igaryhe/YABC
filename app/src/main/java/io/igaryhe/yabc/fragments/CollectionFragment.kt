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
import io.igaryhe.yabc.viewModels.CollectionViewModel
import io.igaryhe.yabc.adapters.CollectionSubjectAdapter
import io.igaryhe.yabc.util.CollectionViewModelFactory
import kotlinx.android.synthetic.main.fragment_collection.*
import io.igaryhe.yabc.databinding.FragmentCollectionBinding

class CollectionFragment(private val type: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // val binding = DataBindingUtil.inflate<FragmentCollectionBinding>(inflater, R.layout.fragment_collection, container, false)
        val view = inflater.inflate(R.layout.fragment_collection, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CollectionSubjectAdapter()
            }
        }
        /*
        val mCollectionViewModel = ViewModelProviders
            .of(this, CollectionViewModelFactory(activity!!.application, type))
            .get(CollectionViewModel::class.java)
        binding.collectionViewModel = mCollectionViewModel
        binding.lifecycleOwner = this
        */
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = col_list.adapter!! as CollectionSubjectAdapter
        val mSubjectViewModel = ViewModelProviders
            .of(this, CollectionViewModelFactory(activity!!.application, type))
            .get(CollectionViewModel::class.java)
        mSubjectViewModel.subjects.observe(this, Observer<List<CollectionSubject>> { t ->
            adapter.setCollectionSubjects(t)
        })
    }
}
