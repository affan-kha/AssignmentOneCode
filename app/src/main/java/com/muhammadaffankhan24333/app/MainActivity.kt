package com.muhammadaffankhan24333.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class MainActivity : AppCompatActivity() {
    private lateinit var buttonCalculate: Button
    private lateinit var tipInputBox:EditText
    private lateinit var textViewTipAmount:TextView
    private lateinit var radioGroup: RadioGroup
    private var tipPercentageValue = 20
    private lateinit var changeTheme:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Constants.selectedThemeIndex ==2){
            setTheme(R.style.CustomTheme)
        }
        setContentView(R.layout.activity_main)

        tipInputBox = findViewById(R.id.amountbox)
        textViewTipAmount = findViewById(R.id.tipview)
        changeTheme = findViewById(R.id.change_theme)
        changeTheme.setOnClickListener {
            startActivity(Intent(this,ChangeThemeActivity::class.java))
        }
        radioGroup = findViewById(R.id.tip_percentage_group)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonCalculate.setOnClickListener {
            val billAmount = tipInputBox.text.toString()
            val tipAmount = calculateTip(billAmount)
            textViewTipAmount.text = "Tip Amount: $${tipAmount}"
        }
//applyTheme(Constants.selectedThemeIndex)
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
//                setTheme(R.style.CustomTheme)
                theme.applyStyle(R.style.CustomTheme,true)
            }
        }
    }


    private fun getSelectedRadioButtonIndex(): Int {
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        return radioGroup.indexOfChild(findViewById(selectedRadioButtonId))
    }


    private fun calculateTip(billAmount: String): Double {
        val bill = billAmount.toDoubleOrNull() ?: 0.0
        tipPercentageValue = when(getSelectedRadioButtonIndex()){
            0->{
                20
            }

            1->{
                18
            }

            else->{
                15
            }
        }
        return (bill * tipPercentageValue)/100
    }
}