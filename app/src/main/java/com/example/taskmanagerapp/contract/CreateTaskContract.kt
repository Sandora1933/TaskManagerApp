package com.example.taskmanagerapp.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.taskmanagerapp.activity.NewTaskActivity

class CreateTaskContract : ActivityResultContract<Int, String>() {

    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, NewTaskActivity::class.java)
            .putExtra("my_input_key", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? = when {
            resultCode != Activity.RESULT_OK -> null
            else -> intent?.getStringExtra("task")
    }


}