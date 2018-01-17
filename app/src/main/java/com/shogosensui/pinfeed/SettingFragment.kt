package com.shogosensui.pinfeed

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.shogosensui.pinfeed.api.PinboardApi
import com.shogosensui.pinfeed.api.ApiClientProvider

class SettingFragment : Fragment() {
    lateinit var userIdText: EditText
    lateinit var passwordText: EditText
    lateinit var saveButton: Button
    lateinit var pinboardApi: PinboardApi

    companion object {
        fun getInstance() : SettingFragment {
            return SettingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pinboardApi = ApiClientProvider.provideApi()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userIdText = view.findViewById(R.id.pinboard_user_id)
        passwordText = view.findViewById(R.id.pinboard_password)
        saveButton = view.findViewById(R.id.save)

        userIdText.setText(MainApplication.preference.userId)
        passwordText.setText(MainApplication.preference.password)

        saveButton.setOnClickListener {
            MainApplication.preference.userId = userIdText.text.toString()
            MainApplication.preference.password = passwordText.text.toString()

            pinboardApi.apiToken(MainApplication.preference.credentials).subscribe({ payload ->
                MainApplication.preference.apiToken = payload.result
            }, { error ->
                Log.e("apiToken", "error", error)
            })

            pinboardApi.secretToken(MainApplication.preference.credentials).subscribe({ payload ->
                MainApplication.preference.secretToken = payload.result
            }, { error ->
                Log.e("secretToken", "error", error)
            })
        }
    }
}