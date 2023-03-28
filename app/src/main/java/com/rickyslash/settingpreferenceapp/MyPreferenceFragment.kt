package com.rickyslash.settingpreferenceapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

class MyPreferenceFragment: PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var NAME: String
    private lateinit var EMAIL: String
    private lateinit var AGE: String
    private lateinit var PHONE: String
    private lateinit var LOVE: String

    private lateinit var namePreference: EditTextPreference
    private lateinit var emailPreference: EditTextPreference
    private lateinit var agePreference: EditTextPreference
    private lateinit var phonePreference: EditTextPreference
    private lateinit var isLoveMetallicaPreference: CheckBoxPreference

    companion object {
        private const val DEFAULT_VALUE = "Empty"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        // setting out the preferenceScreen layout
        addPreferencesFromResource(R.xml.preferences)
        // calling the 'init' to initiate variables
        init()
        setSummaries()
    }

    // initiating variables needed
    private fun init() {
        NAME = resources.getString(R.string.key_name)
        EMAIL = resources.getString(R.string.key_email)
        AGE = resources.getString(R.string.key_age)
        PHONE = resources.getString(R.string.key_phone)
        LOVE = resources.getString(R.string.key_love)

        namePreference = findPreference<EditTextPreference>(NAME) as EditTextPreference
        emailPreference = findPreference<EditTextPreference>(EMAIL) as EditTextPreference
        agePreference = findPreference<EditTextPreference>(AGE) as EditTextPreference
        phonePreference = findPreference<EditTextPreference>(PHONE) as EditTextPreference
        isLoveMetallicaPreference = findPreference<CheckBoxPreference>(LOVE) as CheckBoxPreference
    }

    private fun setSummaries() {
        // 'preferenceManager' is another way to access sharedPreferences, besides `context.getSharedPreference`
        // 'context.getSharedPreference' is preferred when it's needed to access 'SharedPreference' from another class/activity
        // 'preferenceManager' is preferred to access 'sharedPreference' within 'PreferenceFragment' or 'PreferenceActivity' scope
        val sh = preferenceManager.sharedPreferences

        // this will set the 'summary' (text below the title) of a preference item, the 'value' and the 'default value'
        namePreference.summary = sh?.getString(NAME, DEFAULT_VALUE)
        namePreference.summary = sh?.getString(NAME, DEFAULT_VALUE)
        emailPreference.summary = sh?.getString(EMAIL, DEFAULT_VALUE)
        agePreference.summary = sh?.getString(AGE, DEFAULT_VALUE)
        phonePreference.summary = sh?.getString(PHONE, DEFAULT_VALUE)
        isLoveMetallicaPreference.isChecked = sh!!.getBoolean(LOVE, false)
    }

    // this will 'updates the UI' by 'Listener' when the Fragment is 'onResume'
    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    // this will 'stop the Listener' that 'updates the UI' so the system doesn't have memory leaks
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    // this 'called' when the 'preference is changed'
    // this connects to the function inside 'onResume()' and 'onPause()'
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == NAME) {
            namePreference.summary = sharedPreferences?.getString(NAME, DEFAULT_VALUE)
        }
        if (key == EMAIL) {
            emailPreference.summary = sharedPreferences?.getString(EMAIL, DEFAULT_VALUE)
        }
        if (key == AGE) {
            agePreference.summary = sharedPreferences?.getString(AGE, DEFAULT_VALUE)
        }
        if (key == PHONE) {
            phonePreference.summary = sharedPreferences?.getString(PHONE, DEFAULT_VALUE)
        }
        if (key == LOVE) {
            isLoveMetallicaPreference.isChecked = sharedPreferences!!.getBoolean(LOVE, false)
        }
    }

}