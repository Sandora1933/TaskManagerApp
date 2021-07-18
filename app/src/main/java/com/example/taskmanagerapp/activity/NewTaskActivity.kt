package com.example.taskmanagerapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.model.Task
import com.example.taskmanagerapp.model.TaskCategory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_create_task.*

class NewTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

//        val nameEditText: EditText = findViewById(R.id.nameEditText)
//        val categoryButtonText: Button = findViewById(R.id.categoryButtonText)
//        val

        val backArrowButton: ImageButton = findViewById<ImageButton>(R.id.backImageButton)
        val forwardArrowButton: ImageButton = findViewById<ImageButton>(R.id.forwardImageButton)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings_item -> Toast.makeText(this, "settings clicked", Toast.LENGTH_LONG).show()
            R.id.about_item -> Toast.makeText(this, "about clicked", Toast.LENGTH_LONG).show()
        }
        return true
    }

    fun createButtonClicked(view: View) {

        val taskName: String = if (nameEditText.text.toString() != "") nameEditText.text.toString()
        else {
            Toast.makeText(this, "name is empty", LENGTH_SHORT).show()
            return
        }

        val category: TaskCategory = when(categoryButtonText.text.toString()) {
            "JOB" -> TaskCategory.CATEGORY_JOB
            "STUDY" -> TaskCategory.CATEGORY_STUDY
            "SOCIAL" -> TaskCategory.CATEGORY_SOCIAL
            "HOME" -> TaskCategory.CATEGORY_HOME
            "RELAX" -> TaskCategory.CATEGORY_RELAX
            else -> TaskCategory.CATEGORY_OTHER
        }

        val deadline: String = "12.07"
        val description: String = descriptionTextView.text.toString()

        val task: Task =
            Task(
                taskName,
                category,
                deadline,
                description
            )

        val resultIntent: Intent = Intent()
        val taskJson: String = Gson().toJson(task)
        resultIntent.putExtra("task", taskJson)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()

    }

    fun backArrowClicked(view: View) {
        val categoryButton: Button = findViewById(R.id.categoryButtonText)

        when (categoryButton.text.toString()){
            "JOB" -> categoryButton.text = "HOME"
            "HOME" -> categoryButton.text = "SOCIAL"
            "STUDY" -> categoryButton.text = "JOB"
            "OTHER" -> categoryButton.text = "STUDY"
            else -> Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
        }
    }

    fun forwardArrowClicked(view: View) {
        val categoryButton: Button = findViewById(R.id.categoryButtonText)

        when (categoryButton.text.toString()){
            "JOB" -> categoryButton.text = "STUDY"
            "STUDY" -> categoryButton.text = "OTHER"
            "HOME" -> categoryButton.text = "JOB"
            "SOCIAL" -> categoryButton.text = "HOME"
            else -> Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
        }

    }
}