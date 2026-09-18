package com.example.campusbudget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private var expenseList: List<Expense>,
    private val onDeleteClick: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val category: TextView =
            itemView.findViewById(R.id.tvItemCategory)

        val description: TextView =
            itemView.findViewById(R.id.tvItemDescription)

        val amount: TextView =
            itemView.findViewById(R.id.tvItemAmount)

        val deleteButton: Button =
            itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.expense_item,
                parent,
                false
            )

        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ExpenseViewHolder,
        position: Int
    ) {

        val expense = expenseList[position]

        holder.category.text =
            expense.category

        holder.description.text =
            expense.description

        holder.amount.text =
            "₹${String.format("%.2f", expense.amount)}"

        holder.deleteButton.setOnClickListener {
            onDeleteClick(expense)
        }
    }

    override fun getItemCount(): Int =
        expenseList.size

    fun updateList(newList: List<Expense>) {

        expenseList = newList

        notifyDataSetChanged()
    }
}