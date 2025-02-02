package com.route.todoappc41gsunwed.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todoappc41gsunwed.database.model.Task
import com.route.todoappc41gsunwed.databinding.ItemTaskBinding

class TasksAdapter(var taskList: List<Task>? = null) : Adapter<TasksAdapter.TaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding = binding)
    }

    override fun getItemCount(): Int {
        return taskList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = taskList?.get(position) ?: return
        holder.bind(item)
    }

    class TaskViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.title.text = task.title
            binding.time.text = "${task.date}"
        }
    }

}