package com.example.campusbudget

data class Expense(
    val id: Int,
    val amount: Double,
    val category: String,
    val description: String,
    val date: Long
)