    package com.example.expensecalculator

    import android.annotation.SuppressLint
    import android.text.Editable
    import android.text.TextWatcher
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.EditText
    import android.widget.TextView
    import androidx.annotation.LayoutRes
    import androidx.recyclerview.widget.RecyclerView

    class CategoriesAdapter(
        private val categories: MutableMap<>,
        private val listener: OnCategoryChangedListener
    ) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

        private var itemLayout = R.layout.items

        interface OnCategoryChangedListener {
            fun onCategoryChanged(position: Int, budget: Int)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(itemLayout, parent, false)
            return CategoryViewHolder(view)
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val category = categories[position]
            holder.categoryNameEditText.setText(category.name)
            holder.categoryBudgetEditText.setText(category.budget.toString())
            holder.categoryBudgetEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val budget = if (s.toString().isEmpty()) 0 else s.toString().toInt()
                    listener.onCategoryChanged(position, budget)
                }
            })
        }

        override fun getItemCount(): Int {
            return categories.size
        }

        inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val categoryNameEditText: EditText = itemView.findViewById(R.id.category_name_edit_text)
            val categoryBudgetEditText: EditText = itemView.findViewById(R.id.category_budget_edit_text)
        }
    }



