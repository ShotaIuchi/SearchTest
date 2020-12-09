package com.sihuocthai.navigation.searchtest

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (Intent.ACTION_SEARCH == intent.action) {
            doSearchWithIntent(intent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        doSearchWithIntent(intent)
    }

    private fun doSearchWithIntent(queryIntent: Intent) {
        val queryString = queryIntent.getStringExtra(SearchManager.QUERY)
        doSearchWithQuery(queryString)
    }

    private fun doSearchWithQuery(queryString: String?) {
        Log.d("XXXXX", queryString)
    }
}