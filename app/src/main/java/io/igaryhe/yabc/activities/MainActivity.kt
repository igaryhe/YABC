package io.igaryhe.yabc.activities

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import io.igaryhe.yabc.adapters.CategoryPagerAdapter
import io.igaryhe.yabc.R
import io.igaryhe.yabc.databinding.ActivityMainBinding
import io.igaryhe.yabc.databinding.NavHeaderMainBinding
import io.igaryhe.yabc.viewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var appbarMenu: Menu
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        category_pager.adapter = CategoryPagerAdapter(supportFragmentManager)
        cat_tab.setupWithViewPager(category_pager)

        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({ swipe_refresh.isRefreshing = false },1000)
        }
        val mBind: NavHeaderMainBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.nav_header_main, binding.navView, false)
        val mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        mBind.userViewModel = mUserViewModel
        mBind.lifecycleOwner = this
        binding.navView.addHeaderView(mBind.root)
        mUserViewModel.avatar.observe(this, Observer<String> {
                t ->
            Picasso.get().load(t).resize(192, 192).into(avatar)
        })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.options_menu, menu)
        appbarMenu = menu
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean { return false }
                override fun onQueryTextChange(newText: String?): Boolean { return true }
            })
        }

        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle nav_graph view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_discover -> {
                val search = appbarMenu.findItem(R.id.search)
                search.expandActionView()
            }
            R.id.nav_calendar -> {
                val intent = Intent(this, DiscoverActivity::class.java)
                startActivity(intent)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
