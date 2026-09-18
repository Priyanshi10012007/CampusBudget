package com.example.campusbudget

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var database: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_expense)

        database = DatabaseHelper(this)

        val amount =
            findViewById<EditText>(R.id.etAmount)

        val category =
            findViewById<EditText>(R.id.etCategory)

        val description =
            findViewById<EditText>(R.id.etDescription)

        val saveButton =
            findViewById<Button>(R.id.btnSaveExpense)

        saveButton.setOnClickListener {

            val amountText =
                amount.text.toString().trim()

            val categoryText =
                category.text.toString().trim()

            val descriptionText =
                description.text.toString().trim()

            if (
                amountText.isEmpty() ||
                categoryText.isEmpty() ||
                descriptionText.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val amountValue =
                amountText.toDoubleOrNull()

            if (
                amountValue == null ||
                amountValue <= 0
            ) {

                Toast.makeText(
                    this,
                    "Enter a valid amount",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val success = database.addExpense(
                amountValue,
                categoryText,
                descriptionText
            )

            if (success) {

                Toast.makeText(
                    this,
                    "Expense added successfully",
                    Toast.LENGTH_SHORT
                ).show()

                finish()

            } else {

                Toast.makeText(
                    this,
                    "Failed to add expense",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}