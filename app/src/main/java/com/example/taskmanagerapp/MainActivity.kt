package com.example.taskmanagerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

//    val recyclerView: RecyclerView? = null
//    val recyclerViewAdapter: TaskAdapter? = null
//    val layoutManager: RecyclerView.LayoutManager? = null

    lateinit var taskList: List<Task>
    val listType = object : TypeToken<List<Task>>() {}.type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = getSharedPreferences("storage", Context.MODE_PRIVATE).getString("list", null)
        if (jsonString != null){
            taskList = readList(jsonString)

            recyclerView.adapter = TaskAdapter(taskList)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

    }

    override fun onStop() {
        super.onStop()
        saveList()
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

    private fun readList(jsonString: String): List<Task>{
        taskList = Gson().fromJson(jsonString, listType)
        recyclerView.adapter = TaskAdapter(taskList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        return taskList
    }

    private fun saveList(): Unit{
        if (taskList.isNotEmpty()){
            val jsonString: String = Gson().toJson(taskList, listType)
            val editor: SharedPreferences.Editor = getSharedPreferences("storage", Context.MODE_PRIVATE).edit()
            editor.putString("list", jsonString).apply()
        }
    }

    fun floatingButtonClicked(view: View) {
        val intent = Intent(this, NewTaskActivity::class.java)
        startActivity(intent)
    }
}