package com.example.campusbudget

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BudgetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_budget)

        val budgetEditText =
            findViewById<EditText>(R.id.etBudget)

        val currentBudget =
            findViewById<TextView>(R.id.tvCurrentBudget)

        val saveButton =
            findViewById<Button>(R.id.btnSaveBudget)

        val preferences =
            getSharedPreferences(
                "CampusBudget",
                MODE_PRIVATE
            )

        val oldBudget =
            preferences.getFloat(
                "monthly_budget",
                0f
            )

        currentBudget.text =
            "Current Budget: ₹${String.format("%.2f", oldBudget)}"

        saveButton.setOnClickListener {

            val budgetText =
                budgetEditText.text.toString().trim()

            if (budgetText.isEmpty()) {

                Toast.makeText(
                    this,
                    "Enter budget",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val budget =
                budgetText.toFloatOrNull()

            if (
                budget == null ||
                budget <= 0
            ) {

                Toast.makeText(
                    this,
                    "Enter valid budget",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            preferences.edit()
                .putFloat(
                    "monthly_budget",
                    budget
                )
                .apply()

            currentBudget.text =
                "Current Budget: ₹${String.format("%.2f", budget)}"

            budgetEditText.text.clear()

            Toast.makeText(
                this,
                "Budget saved",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}