package com.example.recipeapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var button: Button
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button)
        listView = findViewById(R.id.listView)

        val recipes = resources.getStringArray(R.array.recipes)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, recipes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        button.setOnClickListener {
            val selectedRecipe = spinner.selectedItem.toString()
            val recipeDetails = getRecipeDetails(selectedRecipe)
            val ingredients = recipeDetails.split("\n").drop(1) // Drop the title line
            val checklistAdapter = ArrayAdapter(this, R.layout.checklist_item, ingredients)
            listView.adapter = checklistAdapter
        }
    }

    private fun getRecipeDetails(recipe: String): String {
        return when (recipe) {
            "Pasta" -> getString(R.string.pasta_recipe)
            "Pizza" -> getString(R.string.pizza_recipe)
            "Salad" -> getString(R.string.salad_recipe)
            else -> getString(R.string.select_recipe_prompt)
        }
    }
}
