package com.shogosensui.pinfeed

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class Preference(context: Context) {
    val p: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var userId: String = p.getString("userId", "")
        set(value) {
            p.edit().putString("userId", value).commit()
            field = value
        }

    var password: String = p.getString("password", "")
        set(value) {
            p.edit().putString("password", value).commit()
            field = value
        }

    var apiToken: String = p.getString("apiToken", "")
        set(value) {
            p.edit().putString("apiToken", value).commit()
            field = value
        }

    var secretToken: String = p.getString("secretToken", "")
        set(value) {
            p.edit().putString("secretToken", value).commit()
            field = value
        }
}