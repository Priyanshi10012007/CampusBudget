# CampusBudget 

## 📱 About the Project

CampusBudget is a simple Android application that I developed to help students manage their monthly budget and daily expenses.

The app allows a student to set a monthly budget, add expenses, view transaction history, and check basic expense analysis. A separate parent login is also provided so that parents can view the student's spending information.

This project was developed as part of my Mobile Application Development (MAD) assignment.

---

## 🎯 Main Features

### Student
- Student Login
- Set Monthly Budget
- Add Expense
- View Transaction History
- Delete Expense
- View Expense Analysis
- View Total Spent
- View Remaining Budget

### Parent
- Parent Login
- View Student Budget
- View Student Spending
- View Transactions
- View Expense Analysis

---

## 🛠️ Technologies Used

- Android Studio
- Kotlin
- XML
- SQLite
- SQLiteOpenHelper
- SharedPreferences
- RecyclerView
- Git & GitHub

---

## 🗄️ Database

I used **SQLite** to store expense information locally on the device.

The `expenses` table contains:

| Field | Description |
|---|---|
| id | Unique expense ID |
| amount | Expense amount |
| category | Expense category |
| description | Expense description |
| date | Date and time of expense |

I used **SharedPreferences** to store the monthly budget.

---

## 📱 Application Screens

The application contains the following screens:

1. Login Screen
2. Student Dashboard
3. Parent Dashboard
4. Monthly Budget
5. Add Expense
6. Transaction History
7. Expense Analysis
   <img src="assets/deshbord.png" width="200">
<img src="assets/student_dashbord.png" width="200">
<img src="assets/parent_dashbord.png" width="200">
<img src="assets/budget.png" width="200">
<img src="assets/histroy.png" width="200">
<img src="assets/anaylais.png" width="200">
<img src="assets/add_expances.png" width="200">
<img src="assets/expance_anlaysis.png" width="200">

---

## 🔄 Application Flow

```text
Login
  │
  ├── Student
  │     ↓
  │  Student Dashboard
  │     ├── Set Budget
  │     ├── Add Expense
  │     ├── Transaction History
  │     └── Expense Analysis
  │
  └── Parent
        ↓
     Parent Dashboard
        ├── View Transactions
        └── View Expense Analysis
