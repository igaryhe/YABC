package io.igaryhe.yabc.activities

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import io.igaryhe.yabc.R
import io.igaryhe.yabc.databinding.ActivityMainBinding
import io.igaryhe.yabc.databinding.NavHeaderMainBinding
import io.igaryhe.yabc.viewModels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appbarMenu: Menu
    private lateinit var appBarConfiguration: AppBarConfiguration
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Display user info
        val mBind: NavHeaderMainBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.nav_header_main, binding.navView, false)
        val mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        mBind.userViewModel = mUserViewModel
        mBind.lifecycleOwner = this
        binding.navView.addHeaderView(mBind.root)

        // Setup nav controller
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.discoverFragment), drawer_layout)
        nav_view.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp()
            = findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)

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
}
