package com.example.taskmanagerapp.contract

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.taskmanagerapp.activity.EditTaskActivity

class EditTaskContract : ActivityResultContract<Int, String>() {

    override fun createIntent(context: Context, input: Int?): Intent {
        return Intent(context, EditTaskActivity::class.java)
            .putExtra("my_input_key", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return intent?.getStringExtra("task")
    }


}