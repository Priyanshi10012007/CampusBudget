package com.example.campusbudget

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CampusBudget.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_EXPENSES = "expenses"

        private const val COLUMN_ID = "id"
        private const val COLUMN_AMOUNT = "amount"
        private const val COLUMN_CATEGORY = "category"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE $TABLE_EXPENSES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_AMOUNT REAL NOT NULL,
                $COLUMN_CATEGORY TEXT NOT NULL,
                $COLUMN_DESCRIPTION TEXT NOT NULL,
                $COLUMN_DATE INTEGER NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_EXPENSES")
        onCreate(db)
    }

    fun addExpense(
        amount: Double,
        category: String,
        description: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()

        values.put(COLUMN_AMOUNT, amount)
        values.put(COLUMN_CATEGORY, category)
        values.put(COLUMN_DESCRIPTION, description)
        values.put(COLUMN_DATE, System.currentTimeMillis())

        val result = db.insert(TABLE_EXPENSES, null, values)

        db.close()

        return result != -1L
    }

    fun getAllExpenses(): ArrayList<Expense> {

        val expenseList = ArrayList<Expense>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_EXPENSES ORDER BY $COLUMN_DATE DESC",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))

                val amount =
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT))

                val category =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY))

                val description =
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))

                val date =
                    cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE))

                val expense = Expense(
                    id = id,
                    amount = amount,
                    category = category,
                    description = description,
                    date = date
                )

                expenseList.add(expense)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return expenseList
    }

    fun deleteExpense(id: Int): Boolean {

        val db = writableDatabase

        val result = db.delete(
            TABLE_EXPENSES,
            "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )

        db.close()

        return result > 0
    }

    fun getTotalSpent(): Double {

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT SUM($COLUMN_AMOUNT) FROM $TABLE_EXPENSES",
            null
        )

        var total = 0.0

        if (cursor.moveToFirst()) {
            if (!cursor.isNull(0)) {
                total = cursor.getDouble(0)
            }
        }

        cursor.close()
        db.close()

        return total
    }
}