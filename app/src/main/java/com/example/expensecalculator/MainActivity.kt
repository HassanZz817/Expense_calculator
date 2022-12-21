package com.example.expensecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var salaryEditText: EditText
    private lateinit var addCategoryButton: Button
    private lateinit var categoriesRecyclerView: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private var categories = mutableListOf<Category>()
    private var totalBudget = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        salaryEditText = findViewById(R.id.salary_edit_text)
        addCategoryButton = findViewById(R.id.add_category_button)
        categoriesRecyclerView = findViewById(R.id.categories_recycler_view)

        categoriesAdapter = CategoriesAdapter(categories, object : CategoriesAdapter.OnCategoryChangedListener {
            override fun onCategoryChanged(position: Int, budget: Int) {
                categories[position].budget = budget
                updateTotalBudget()
            }
        })
        categoriesAdapter.setItemLayout(R.layout.items)
        categoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        categoriesRecyclerView.adapter = categoriesAdapter

        addCategoryButton.setOnClickListener {
            val categoryName = UUID.randomUUID().toString()
            categories.add(Category(categoryName, 0))
            categoriesAdapter.notifyDataSetChanged()
        }
    }

    private fun updateTotalBudget() {
        totalBudget = 0
        for (category in categories) {
            totalBudget += category.budget
        }
        if (totalBudget > salaryEditText.text.toString().toInt()) {
            Toast.makeText(this, "Total budget cannot exceed salary!", Toast.LENGTH_SHORT).show()
        }
    }
}


