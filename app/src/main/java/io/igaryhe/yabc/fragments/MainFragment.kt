package io.igaryhe.yabc.fragments

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.igaryhe.yabc.R
import io.igaryhe.yabc.adapters.CategoryPagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category_pager.adapter = CategoryPagerAdapter(activity!!.supportFragmentManager)
        cat_tab.setupWithViewPager(category_pager)

        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({ swipe_refresh.isRefreshing = false },1000)
        }
    }
}