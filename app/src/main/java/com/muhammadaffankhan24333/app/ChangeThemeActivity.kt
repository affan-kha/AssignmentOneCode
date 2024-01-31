package com.muhammadaffankhan24333.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate

class ChangeThemeActivity : AppCompatActivity() {

    private lateinit var themeRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_theme)

        themeRadioGroup = findViewById(R.id.themeRadioGroup)
        when(Constants.selectedThemeIndex){
            0->{
                themeRadioGroup.check(R.id.lightModeRadioButton)
            }
            1->{
                themeRadioGroup.check(R.id.darkModeRadioButton)
            }
            else->{
                themeRadioGroup.check(R.id.customThemeRadioButton)
            }
        }
        themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Apply the selected theme based on the checked RadioButton
             if (checkedId == R.id.lightModeRadioButton){
                 Constants.selectedThemeIndex = 0
             }
            else if (checkedId == R.id.darkModeRadioButton){
                Constants.selectedThemeIndex = 1
             }
            else{
                Constants.selectedThemeIndex = 2
             }
            applyTheme(Constants.selectedThemeIndex)
        }
    }

    private fun applyTheme(themePosition: Int) {
        when (themePosition) {
            0 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            1 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            2 -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                setTheme(R.style.CustomTheme)
                theme.applyStyle(R.style.CustomTheme,true)
            }
        }
        Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }.apply {
            finish()
        }
    }
}