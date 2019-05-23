package io.igaryhe.yabc

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_discover.*
import kotlinx.android.synthetic.main.activity_discover.swipe_refresh

class DiscoverActivity : AppCompatActivity()  {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.BgmLightTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        calendar_pager.adapter = CalendarPagerAdapter(supportFragmentManager)
        cal_tab.setupWithViewPager(calendar_pager)
        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({ swipe_refresh.isRefreshing = false },1000)
        }
    }
}