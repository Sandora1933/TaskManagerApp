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
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Type
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity(), TaskAdapter.OnItemClickListener {

//    val recyclerView: RecyclerView? = null
//    val recyclerViewAdapter: TaskAdapter? = null
//    val layoutManager: RecyclerView.LayoutManager? = null

    var taskList: ArrayList<Task> = ArrayList<Task>()
    val listType = object : TypeToken<ArrayList<Task>>() {}.type
    val taskType = object : TypeToken<Task>() {}.type

    lateinit var adapter: TaskAdapter

    val activityLauncher = registerForActivityResult(CreateTaskContract()) { result ->
        val task: Task = Gson().fromJson(result, taskType)
        taskList.add(task)
        Toast.makeText(this, taskList.size.toString(), Toast.LENGTH_SHORT).show()
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = getSharedPreferences("storage", Context.MODE_PRIVATE).getString("list", null)
        taskList = if (jsonString != null) readList(jsonString) as ArrayList<Task> else ArrayList<Task>()

        val emptyTextView: TextView = findViewById<TextView>(R.id.emptyTextView)

        if (taskList.size == 0){
            emptyTextView.text = "Empty list..."
        }
        else {
            emptyTextView.text = ""
        }

        adapter = TaskAdapter(taskList, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



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
            R.id.settings_item -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.about_item -> startActivity(Intent(this, AboutActivity::class.java))
        }
        return true
    }

    private fun readList(jsonString: String): ArrayList<Task>{
        taskList = Gson().fromJson(jsonString, listType)
//        recyclerView.adapter = TaskAdapter(taskList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        val l: ArrayList<Task> = ArrayList<Task>()
//        l.add(Task("asd1", TaskCategory.CATEGORY_OTHER, "asd", "asdas"))
//        l.add(Task("asd2", TaskCategory.CATEGORY_OTHER, "asd", "asdas"))
//        l.add(Task("asd3", TaskCategory.CATEGORY_OTHER, "asd", "asdas"))

        return Gson().fromJson(jsonString, listType)
        //return l
    }

    private fun saveList(): Unit{
        if (taskList.isNotEmpty()){
            val jsonString: String = Gson().toJson(taskList, listType)
            val editor: SharedPreferences.Editor = getSharedPreferences("storage", Context.MODE_PRIVATE).edit()
            editor.putString("list", jsonString).apply()
        }
    }

    fun floatingButtonClicked(view: View) {
        activityLauncher.launch(10)
    }

    override fun onItemClick(pos: Int) {
        Toast.makeText(this, "" + pos, Toast.LENGTH_SHORT).show()
    }
}