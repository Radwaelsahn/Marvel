package com.marvel.radwa.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.marvel.radwa.App
import com.marvel.radwa.utils.Constants

//todo we should use this as a dagger module
object SharedPrefHelper {

//    private val KEY_PREFS = "baims_new.prefs"//"baims.prefs"

//        if (BuildConfig.IS_LIFE) "life.prefs" else if (BuildConfig.IS_BAIMS) "baims.prefs" else "gsi.prefs"

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = App.context.getSharedPreferences(Constants.KEY_PREFS, Context.MODE_PRIVATE)
    }

    fun setIntoSharedPref(context: Context?, key: String, value: String, prefName: String) {

        val sharedPref = context?.getSharedPreferences(prefName, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getFromSharedPref(context: Context?, key: String, default: String, prefName: String): String? {

        val sharedPref = context?.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return sharedPref?.getString(key, default)
    }


    fun setIntoSharedPref(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()

    fun getFromSharedPref(key: String, default: String? = ""): String = sharedPreferences.getString(key, default) ?: ""

    fun setIntoSharedPrefLong(key: String, value: Long) = sharedPreferences.edit().putLong(key, value).apply()

    fun getFromSharedPrefLong(key: String): Long = sharedPreferences.getLong(key, 0L)

    fun clearCache() = sharedPreferences.edit()?.clear()?.apply()

    fun removeSharedPref(key: String) = sharedPreferences.edit().remove(key).apply()

    fun isDashboardShownBefore(): Boolean = sharedPreferences.getBoolean(DASHBOARD_SEEN, false)

    fun setDashboardShown() = sharedPreferences.edit().putBoolean(DASHBOARD_SEEN, true).apply()

    fun setIntoSharedPrefInt(key: String, value: Int) = sharedPreferences.edit().putInt(key, value).apply()

    fun getFromSharedPrefInt(key: String): Int = sharedPreferences.getInt(key, 0)

    private const val DASHBOARD_SEEN = "is_dashboard_seen"

}
