package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import java.nio.file.StandardWatchEventKinds
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var editTextAmount: EditText
    private lateinit var editTextTipPercent: EditText
    private lateinit var checkBoxRoundBill: CheckBox
    private lateinit var checkBoxRoundTip: CheckBox
    private lateinit var btnCalculate: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextAmount = findViewById<EditText>(R.id.editTextAmount)
        editTextTipPercent = findViewById<EditText>(R.id.editTextTipPercent)
        checkBoxRoundBill = findViewById<CheckBox>(R.id.checkBoxRoundBill)
        checkBoxRoundTip = findViewById<CheckBox>(R.id.checkBoxRoundTip)
        btnCalculate = findViewById<Button>(R.id.btnCalculate)
        textViewResult = findViewById<TextView>(R.id.textViewResult)

        btnCalculate.setOnClickListener { calculateTip() }
    }


    private fun calculateTip() {
        val billAmount = editTextAmount.text.toString().toDoubleOrNull() ?: 0.0
        val tipPercentage = editTextTipPercent.text.toString().toDoubleOrNull() ?: 0.0
        val roundTotal = checkBoxRoundBill.isChecked
        val roundTip = checkBoxRoundTip.isChecked
        val tip = calculateTipAmount(billAmount, tipPercentage, roundTotal, roundTip)
        val totalAmount = billAmount + tip

        textViewResult.text = "Tip: $$tip\nTotal Bill: $$totalAmount"
    }

    private fun calculateTipAmount(
        billAmount: Double,
        tipPercentage: Double,
        roundTotal: Boolean,
        roundTip: Boolean
    ): Double{
        val tipAmount = billAmount * (tipPercentage / 100.0)

        if (roundTotal) {
            val roundedTotal = (billAmount + tipAmount).roundToInt().toDouble()
            return roundedTotal - billAmount
        }
        else if (roundTip){
            val roundedTip = tipAmount.roundToInt().toDouble()
            return roundedTip
        }
        else{
            return tipAmount
        }
    }
}