package io.igaryhe.yabc

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.appcompat.widget.SearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var appbarMenu: Menu
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        category_pager.adapter = CategoryPagerAdapter(supportFragmentManager)
        cat_tab.setupWithViewPager(category_pager)

        swipe_refresh.setOnRefreshListener {
            Handler().postDelayed({ swipe_refresh.isRefreshing = false },1000)
        }

        val sp = getSharedPreferences("token", Context.MODE_PRIVATE)
        val userId = sp.getInt("user_id", 0)
        val api = BgmService.create()
        api.getUserCollection(userId, "watching_all")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({result ->
                val anime = supportFragmentManager.findFragmentByTag("android:switcher:"
                        + R.id.category_pager + ":" + 0) as SubjectFragment
                /*
                val book = supportFragmentManager.findFragmentByTag("android:switcher:"
                        + R.id.category_pager + ":" + 1) as SubjectFragment
                val drama = supportFragmentManager.findFragmentByTag("android:switcher:"
                        + R.id.category_pager + ":" + 2) as SubjectFragment
                 */
                val animes: MutableList<CollectionSubject> = mutableListOf()
                val books: MutableList<CollectionSubject> = mutableListOf()
                val dramas: MutableList<CollectionSubject> = mutableListOf()
                for (subject: CollectionSubject in result) {
                    when (subject.subject.type) {
                        1 -> books.add(subject)
                        2 -> animes.add(subject)
                        6 -> dramas.add(subject)
                    }
                }
                anime.setAdapter(animes)
                // book.setAdapter(books)
                // drama.setAdapter(dramas)
            }, { error -> error.printStackTrace() })
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
        // Handle navigation view item clicks here.
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
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
