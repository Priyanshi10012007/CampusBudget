package com.example.campusbudget

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnalysisActivity : AppCompatActivity() {

    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_analysis)

        database = DatabaseHelper(this)

        loadAnalysis()
    }

    private fun loadAnalysis() {

        val totalSpent =
            database.getTotalSpent()

        val preferences =
            getSharedPreferences(
                "CampusBudget",
                MODE_PRIVATE
            )

        val budget =
            preferences.getFloat(
                "monthly_budget",
                0f
            ).toDouble()

        val remaining =
            budget - totalSpent

        val expenses =
            database.getAllExpenses()

        val categoryTotals =
            expenses
                .groupBy { it.category }
                .mapValues { entry ->
                    entry.value.sumOf {
                        it.amount
                    }
                }

        val budgetText =
            findViewById<TextView>(
                R.id.tvAnalysisBudget
            )

        val spentText =
            findViewById<TextView>(
                R.id.tvAnalysisSpent
            )

        val remainingText =
            findViewById<TextView>(
                R.id.tvAnalysisRemaining
            )

        val categoryText =
            findViewById<TextView>(
                R.id.tvCategoryAnalysis
            )

        budgetText.text =
            "Budget: ₹${String.format("%.2f", budget)}"

        spentText.text =
            "Spent: ₹${String.format("%.2f", totalSpent)}"

        remainingText.text =
            "Remaining: ₹${String.format("%.2f", remaining)}"

        if (categoryTotals.isEmpty()) {

            categoryText.text =
                "No expenses yet"

        } else {

            val result =
                StringBuilder()

            categoryTotals.forEach {
                    (category, amount) ->

                result.append(
                    "$category : ₹${String.format("%.2f", amount)}\n\n"
                )
            }

            categoryText.text =
                result.toString()
        }
    }

    override fun onResume() {

        super.onResume()

        if (::database.isInitialized) {
            loadAnalysis()
        }
    }
}