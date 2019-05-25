package io.igaryhe.yabc.fragments


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.igaryhe.yabc.R
import io.igaryhe.yabc.adapters.CalendarPagerAdapter
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_discover, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar_pager.adapter = CalendarPagerAdapter(activity!!.supportFragmentManager)
        cal_tab.setupWithViewPager(calendar_pager)
        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({ swipe_refresh.isRefreshing = false },1000)
        }
    }
}
