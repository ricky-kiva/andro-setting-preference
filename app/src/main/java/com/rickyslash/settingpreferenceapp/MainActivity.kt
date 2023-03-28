package com.rickyslash.settingpreferenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // connecting fragment to activity's ,layout element
        supportFragmentManager
            .beginTransaction()
            .add(R.id.setting_holder, MyPreferenceFragment())
            .commit()
    }
}