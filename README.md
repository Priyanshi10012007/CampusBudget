# Student Expense Tracker

## About the Project

Student Expense Tracker is a simple Android application developed using Android Studio and Kotlin. The purpose of this project is to help students record their daily expenses and keep track of their monthly spending.

The application allows users to add expenses by entering the amount, category, and date. The expenses are saved locally and can be viewed or deleted later. Users can also set a monthly budget and check their total spending and remaining budget.

## Features

* Add new expenses
* Enter expense amount
* Enter category manually
* Enter date manually
* View saved expenses
* Delete expenses
* Set monthly budget
* Calculate total spending
* Calculate remaining budget
* Store data locally

## Screens

### 1. Home Screen

Shows:

* Total Spent
* Monthly Budget
* Remaining Budget
* Add Expense
* View Expenses
* Set Budget

### 2. Add Expense

Users can enter:

* Amount
* Category
* Date

### 3. View Expenses

Displays all saved expenses using RecyclerView. Each expense also has a Delete button.

### 4. Monthly Budget

Allows the user to enter and save a monthly budget.

## Technologies Used

* **Android Studio** – Development environment
* **Kotlin** – Programming language
* **XML** – User interface design
* **RecyclerView** – Displaying expenses
* **SharedPreferences** – Local data storage
* **JSON** – Storing expense data
* **Intent** – Moving between screens
* **Toast** – Showing messages

## Data Storage

The application does not use an online database. Expense and budget data are stored locally on the device using SharedPreferences.

Expense details are stored in JSON format, for example:

```json
[
  {
    "amount": 250,
    "category": "Food",
    "date": "15/09/2026"
  }
]
```

## Calculation

The total spending is calculated by adding all saved expenses.

**Remaining Budget = Monthly Budget − Total Spent**

For example:

```text
Monthly Budget = ₹2000
Total Spent    = ₹500
Remaining      = ₹1500
```

## Project Structure

```text
StudentExpense
│
├── app
│   └── src
│       └── main
│           ├── java
│           │   └── com.example.studentexpense
│           │       ├── MainActivity.kt
│           │       ├── AddExpenseActivity.kt
│           │       ├── ExpenseActivity.kt
│           │       ├── ExpenseAdapter.kt
│           │       └── BudgetActivity.kt
│           │
│           └── res
│               ├── drawable
│               └── layout
│
├── README.md
├── build.gradle.kts
└── settings.gradle.kts
```

## Requirements

* Android Studio
* Kotlin
* Android SDK
* Android device or emulator
* Minimum SDK: API 24

## How to Run

1. Download or clone this repository.
2. Open the project in Android Studio.
3. Wait for Gradle sync to complete.
4. Connect an Android device or start an emulator.
5. Click the **Run** button.
6. The application will start on the device.

## Future Improvements

The project can be improved in the future by adding:

* Expense charts
* Search and filter options
* Edit expense
* Monthly reports
* Export expenses
* Notifications and reminders

## Author

**Priyanshi Modi**

**Project:** Student Expense Tracker
**Platform:** Android
**Language:** Kotlin
**IDE:** Android Studio

## Purpose

This project was developed as a college project to understand the basics of Android application development and to practice working with Android components, layouts, local storage, and multiple activities.
