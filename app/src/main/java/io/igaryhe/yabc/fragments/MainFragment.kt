package io.igaryhe.yabc.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import io.igaryhe.yabc.R
import io.igaryhe.yabc.activities.MainActivity
import io.igaryhe.yabc.adapters.CategoryPagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private var v: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_main, container, false)
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // category_pager.offscreenPageLimit = 3
        category_pager.adapter = CategoryPagerAdapter(activity!!.supportFragmentManager)
        cat_tab.setupWithViewPager(category_pager)

        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({ swipe_refresh.isRefreshing = false },1000)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.options_menu, menu)
        val item = menu.findItem(R.id.search)
        val searchView = SearchView((context as MainActivity).supportActionBar!!.themedContext)
        item.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val action = MainFragmentDirections.actionMainToSearch(searchView.query.toString())
                findNavController().navigate(action)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}