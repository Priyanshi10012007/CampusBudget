package com.example.campusbudget

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    private lateinit var database: DatabaseHelper
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_history)

        database = DatabaseHelper(this)

        val recyclerView =
            findViewById<RecyclerView>(
                R.id.recyclerViewExpenses
            )

        adapter = ExpenseAdapter(
            emptyList()
        ) { expense ->

            deleteExpense(expense)
        }

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter = adapter

        loadExpenses()
    }

    private fun loadExpenses() {

        val expenses =
            database.getAllExpenses()

        adapter.updateList(expenses)
    }

    private fun deleteExpense(
        expense: Expense
    ) {

        val deleted =
            database.deleteExpense(expense.id)

        if (deleted) {

            Toast.makeText(
                this,
                "Expense deleted",
                Toast.LENGTH_SHORT
            ).show()

            loadExpenses()
        }
    }

    override fun onResume() {

        super.onResume()

        if (::adapter.isInitialized) {
            loadExpenses()
        }
    }
}