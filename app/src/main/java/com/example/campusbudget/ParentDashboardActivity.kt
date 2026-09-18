package com.example.campusbudget

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ParentDashboardActivity : AppCompatActivity() {

    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_parent_dashboard
        )

        database = DatabaseHelper(this)

        val historyButton =
            findViewById<Button>(
                R.id.btnParentHistory
            )

        val analysisButton =
            findViewById<Button>(
                R.id.btnParentAnalysis
            )

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

        loadParentData()
    }

    private fun loadParentData() {

        val spent =
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
            budget - spent

        findViewById<TextView>(
            R.id.tvParentBudget
        ).text =
            "Budget: ₹${String.format("%.2f", budget)}"

        findViewById<TextView>(
            R.id.tvParentSpent
        ).text =
            "Spent: ₹${String.format("%.2f", spent)}"

        findViewById<TextView>(
            R.id.tvParentRemaining
        ).text =
            "Remaining: ₹${String.format("%.2f", remaining)}"
    }

    override fun onResume() {

        super.onResume()

        if (::database.isInitialized) {
            loadParentData()
        }
    }
}