package com.shogosensui.pinfeed

import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class SettingFragment : Fragment() {
    lateinit var setting: SharedPreferences
    lateinit var userIdText: EditText
    lateinit var passwordText: EditText
    lateinit var saveButton: Button

    companion object {
        fun getInstance() : SettingFragment {
            return SettingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setting = context.getSharedPreferences(getString(R.string.setting_pinboard), Context.MODE_PRIVATE)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userIdText = view.findViewById(R.id.pinboard_user_id)
        passwordText = view.findViewById(R.id.pinboard_password)
        saveButton = view.findViewById(R.id.save)

        userIdText.setText(setting.getString(getString(R.string.setting_user_id), ""))
        passwordText.setText(setting.getString(getString(R.string.setting_password), ""))
        saveButton.setOnClickListener {
            var edit = setting.edit()
            edit.putString(getString(R.string.setting_user_id), userIdText.text.toString())
            edit.putString(getString(R.string.setting_password), passwordText.text.toString())
            edit.commit()
        }
    }
}