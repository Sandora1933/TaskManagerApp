package com.example.taskmanagerapp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.model.Status
import com.example.taskmanagerapp.model.Task
import com.example.taskmanagerapp.model.TaskCategory

class TaskAdapter(private val taskList: ArrayList<Task>, val listener: OnItemClickListener)
    : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
        val taskNameTextView: TextView = itemView.findViewById(R.id.taskName)
        val taskCategoryTextView: TextView = itemView.findViewById(R.id.taskCategory)
        val deadlineTextView: TextView = itemView.findViewById(R.id.deadlineTextView)
        val statusImageView: ImageView = itemView.findViewById(R.id.statusView)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.card_item,
            parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task: Task = taskList.get(position)

        holder.taskNameTextView.setText(task.taskName)
        holder.taskCategoryTextView.setText(task.taskCategory.name)
        holder.deadlineTextView.setText(task.deadline)

        when (task.taskCategory){
            TaskCategory.CATEGORY_JOB -> holder.taskCategoryTextView.setText("job")
            TaskCategory.CATEGORY_HOME -> holder.taskCategoryTextView.setText("home")
            TaskCategory.CATEGORY_RELAX -> holder.taskCategoryTextView.setText("relax")
            TaskCategory.CATEGORY_STUDY -> holder.taskCategoryTextView.setText("study")
            TaskCategory.CATEGORY_SOCIAL -> holder.taskCategoryTextView.setText("social")
            TaskCategory.CATEGORY_OTHER -> holder.taskCategoryTextView.setText("other")
            TaskCategory.CATEGORY_NOT_SPECIFIED -> holder.taskCategoryTextView.setText("not specified")
        }

        when (task.status){
            Status.STATUS_IN_PROCESS -> holder.statusImageView.setImageResource(R.drawable.ic_in_process_24)
            Status.STATUS_DONE -> holder.statusImageView.setImageResource(R.drawable.ic_baseline_check_circle_24)
            Status.STATUS_CANCELLED -> holder.statusImageView.setImageResource(R.drawable.ic_bucket_24)
            Status.STATUS_EXPIRED -> holder.statusImageView.setImageResource(R.drawable.ic_baseline_timer_off_24)
        }

    }

    override fun getItemCount(): Int {
        return taskList.size;
    }

}