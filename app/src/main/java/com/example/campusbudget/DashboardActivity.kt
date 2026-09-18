package com.example.campusbudget

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        database = DatabaseHelper(this)

        val budgetButton =
            findViewById<Button>(R.id.btnBudget)

        val expenseButton =
            findViewById<Button>(R.id.btnAddExpense)

        val historyButton =
            findViewById<Button>(R.id.btnHistory)

        val analysisButton =
            findViewById<Button>(R.id.btnAnalysis)

        budgetButton.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    BudgetActivity::class.java
                )
            )
        }

        expenseButton.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AddExpenseActivity::class.java
                )
            )
        }

        historyButton.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    HistoryActivity::class.java
                )
            )
        }

        analysisButton.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AnalysisActivity::class.java
                )
            )
        }
    }

    private fun loadDashboardData() {

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

        findViewById<TextView>(
            R.id.tvDashboardBudget
        ).text =
            "Budget: ₹${String.format("%.2f", budget)}"

        findViewById<TextView>(
            R.id.tvDashboardSpent
        ).text =
            "Spent: ₹${String.format("%.2f", totalSpent)}"

        findViewById<TextView>(
            R.id.tvDashboardRemaining
        ).text =
            "Remaining: ₹${String.format("%.2f", remaining)}"
    }

    override fun onResume() {

        super.onResume()

        if (::database.isInitialized) {
            loadDashboardData()
        }
    }
}