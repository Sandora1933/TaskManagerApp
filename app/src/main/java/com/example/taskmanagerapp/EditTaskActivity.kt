package com.example.taskmanagerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_create_task.*
import kotlinx.android.synthetic.main.activity_edit_task.*

class EditTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings_item -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.about_item -> startActivity(Intent(this, AboutActivity::class.java))
        }
        return true
    }

    fun deleteButtonClicked(view: View) {
        val resultIntent: Intent = Intent()
        val delete: String = "delete"
        resultIntent.putExtra("task", delete)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    fun editButtonClicked(view: View) {

        val taskName: String = if (enameEditText.text.toString() != "") enameEditText.text.toString()
        else {
            Toast.makeText(this, "name is empty", Toast.LENGTH_SHORT).show()
            return
        }

        val category: TaskCategory = when(ecategoryButtonText.text.toString()) {
            "JOB" -> TaskCategory.CATEGORY_JOB
            "STUDY" -> TaskCategory.CATEGORY_STUDY
            "SOCIAL" -> TaskCategory.CATEGORY_SOCIAL
            "HOME" -> TaskCategory.CATEGORY_HOME
            "RELAX" -> TaskCategory.CATEGORY_RELAX
            else -> TaskCategory.CATEGORY_OTHER
        }

        val deadline: String = "12.07"
        val description: String = edescriptionTextView.text.toString()
        val task: Task = Task(taskName, category, deadline, description)

        val resultIntent: Intent = Intent()
        val taskJson: String = Gson().toJson(task)
        resultIntent.putExtra("task", taskJson)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }

    fun ebackArrowClicked(view: View) {
        val ecategoryButton: Button = findViewById(R.id.ecategoryButtonText)

        when (ecategoryButton.text.toString()){
            "JOB" -> ecategoryButton.text = "HOME"
            "HOME" -> ecategoryButton.text = "SOCIAL"
            "STUDY" -> ecategoryButton.text = "JOB"
            "OTHER" -> ecategoryButton.text = "STUDY"
            else -> Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
        }
    }

    fun eforwardArrowClicked(view: View) {
        val ecategoryButton: Button = findViewById(R.id.ecategoryButtonText)

        when (ecategoryButton.text.toString()){
            "JOB" -> ecategoryButton.text = "STUDY"
            "STUDY" -> ecategoryButton.text = "OTHER"
            "HOME" -> ecategoryButton.text = "JOB"
            "SOCIAL" -> ecategoryButton.text = "HOME"
            else -> Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()
        }

    }

}