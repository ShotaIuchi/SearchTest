package com.sihuocthai.navigation.searchtest

import android.app.SearchManager
import android.content.ComponentName
import android.content.Intent
import android.content.SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
import android.database.Cursor
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val search = findViewById<SearchView>(R.id.search)

        val searchManager = this.getSystemService(SearchManager::class.java)
        val info = searchManager!!.getSearchableInfo(
            ComponentName(
                this,
                MainActivity::class.java
            )
        )
        search.setSearchableInfo(info)

        search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val suggestion = SearchRecentSuggestions(this@MainActivity, "com.sihuocthai.navigation.searchtest.PoiSuggestionProvider", DATABASE_MODE_QUERIES)
                suggestion.saveRecentQuery(query, null)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        search.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                Log.d("XXXXXA1", "$position")
                return true
            }

            override fun onSuggestionClick(position: Int): Boolean {
                Log.d("XXXXXA2", "$position")
                val c: Cursor = search.suggestionsAdapter.cursor
                if (c.moveToPosition(position)) {
                    Log.d("XXXXXA3", c.getString(2))
                    return true
                }
                return true
            }

        })


    }
}