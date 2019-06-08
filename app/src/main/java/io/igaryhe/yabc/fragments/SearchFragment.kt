package io.igaryhe.yabc.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.igaryhe.yabc.R
import io.igaryhe.yabc.adapters.SubjectSmallAdapter
import io.igaryhe.yabc.models.SubjectSmall
import io.igaryhe.yabc.util.SearchViewModelFactory
import io.igaryhe.yabc.viewModels.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.subject_card.view.*

class SearchFragment : Fragment() {
    val args: SearchFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
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
        val mSearchViewModel = ViewModelProviders
            .of(this, SearchViewModelFactory(args.query.toString()))
            .get(SearchViewModel::class.java)
        val adapter = search_list.adapter as SubjectSmallAdapter
        mSearchViewModel.subjects.observe(this, Observer<List<SubjectSmall>> {
            adapter.submitList(it)
            adapter.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as RecyclerView.ViewHolder
                val position = viewHolder.adapterPosition
                val id = it[position].id
                val action = SearchFragmentDirections.actionSearchToSubject(id, it[position].images?.large)
                val extra = FragmentNavigatorExtras(viewHolder.itemView.subjectCover to "$id")
                view.findNavController().navigate(action, extra)
            }
        })
    }
}