package com.shogosensui.pinfeed

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.shogosensui.pinfeed.payload.ApiTokenPayload
import com.shogosensui.pinfeed.payload.SecretTokenPayload
import com.shogosensui.pinfeed.service.PinboardApiService
import com.shogosensui.pinfeed.service.ServiceClientProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingFragment : Fragment() {
    lateinit var userIdText: EditText
    lateinit var passwordText: EditText
    lateinit var saveButton: Button
    lateinit var apiClient: PinboardApiService

    companion object {
        fun getInstance() : SettingFragment {
            return SettingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiClient = ServiceClientProvider.provideApiService()
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

            apiClient.apiToken(PinboardApiService.credentials).enqueue(object : Callback<ApiTokenPayload> {
                override fun onResponse(call: Call<ApiTokenPayload>, response: Response<ApiTokenPayload>) {
                    response.body()?.let {
                        MainApplication.preference.apiToken = it.result
                    }
                }

                override fun onFailure(call: Call<ApiTokenPayload>, t: Throwable?) {
                    Log.e("onFailure", "Failed to get api token")
                }
            })

            apiClient.secretToken(PinboardApiService.credentials).enqueue(object : Callback<SecretTokenPayload> {
                override fun onResponse(call: Call<SecretTokenPayload>, response: Response<SecretTokenPayload>) {
                    response.body()?.let {
                        MainApplication.preference.secretToken = it.result
                    }
                }

                override fun onFailure(call: Call<SecretTokenPayload>, t: Throwable?) {
                    Log.e("onFailure", "Failed to get secret token")
                }
            })
        }
    }
}