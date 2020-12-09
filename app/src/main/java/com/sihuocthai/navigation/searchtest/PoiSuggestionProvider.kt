package com.sihuocthai.navigation.searchtest

import android.app.SearchManager
import android.content.ContentValues
import android.content.SearchRecentSuggestionsProvider
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.provider.BaseColumns
import android.util.Log

class PoiSuggestionProvider : SearchRecentSuggestionsProvider() {

    init {
        setupSuggestions("com.sihuocthai.navigation.searchtest.PoiSuggestionProvider", DATABASE_MODE_QUERIES)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d("XXXXX1", "$uri|$values")
        return super.insert(uri, values)
    }

    override fun setupSuggestions(authority: String?, mode: Int) {
        Log.d("XXXXX1", "$authority|$mode")
        super.setupSuggestions(authority, mode)
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        Log.d("XXXXX3", "$uri|$selection|$sortOrder")
        val c = super.query(uri, projection, selection, selectionArgs, sortOrder)
        c?.let { c ->
            val rowcount: Int = c.count
            val sb = StringBuffer()
            c.moveToFirst()

            for (i in 0 until rowcount) {
                c.moveToNext()
            }
            c.moveToFirst()
            Log.d("XXXXX4", "$c")
        }

        val ccc = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
        ccc.addRow(arrayOf(1, "aiueo1"))
        ccc.addRow(arrayOf(2, "aiueo0"))
        ccc.addRow(arrayOf(3, "aiueo2"))
        ccc.addRow(arrayOf(4, "aiueo4"))

        return ccc
    }

}