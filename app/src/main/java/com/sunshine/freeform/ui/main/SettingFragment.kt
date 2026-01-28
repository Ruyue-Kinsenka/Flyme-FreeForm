package com.sunshine.freeform.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.sunshine.freeform.R
import com.sunshine.freeform.app.MiFreeform
import com.sunshine.freeform.service.ForegroundService

class SettingFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener,
    Preference.OnPreferenceChangeListener {

    private lateinit var sp: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.sharedPreferencesName = MiFreeform.APP_SETTINGS_NAME
        preferenceManager.sharedPreferencesMode = Context.MODE_PRIVATE
        setPreferencesFromResource(R.xml.settings, null)

        sp = requireActivity().getSharedPreferences(MiFreeform.APP_SETTINGS_NAME, Context.MODE_PRIVATE)

        findPreference<SwitchPreference>(SHOW_FLOATING)!!.onPreferenceChangeListener = this
    }

    override fun onPreferenceClick(preference: Preference): Boolean {
        return true
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        when(preference.key) {
            SHOW_FLOATING -> {
                if (newValue as Boolean) {
                    requireContext().startForegroundService(Intent(requireContext(), ForegroundService::class.java))
                }
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
    }

    companion object {
        private const val SHOW_FLOATING = "show_floating"
    }
}
