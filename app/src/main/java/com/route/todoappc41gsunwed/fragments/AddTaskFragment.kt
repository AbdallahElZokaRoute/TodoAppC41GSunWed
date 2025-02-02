package com.route.todoappc41gsunwed.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todoappc41gsunwed.R
import com.route.todoappc41gsunwed.database.TaskDatabase
import com.route.todoappc41gsunwed.database.model.Task
import com.route.todoappc41gsunwed.databinding.FragmentAddTaskBinding
import com.route.todoappc41gsunwed.fragments.callbacks.OnTodoAddedListener
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment() {
    var onTodoAddedListener: OnTodoAddedListener? = null
    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()
        binding.addTaskBtn.setOnClickListener {
            if (validateInputs())
                addTaskIntoDataBase()
        }
        binding.selectDateTv.setOnClickListener {
            val datePickerDialog =
                DatePickerDialog(
                    requireActivity(),
                    { view, year, month, dayOfMonth ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH, month)
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        binding.selectDateTv.text = "$dayOfMonth/${month + 1}/$year"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
            datePickerDialog.datePicker.minDate = calendar.timeInMillis
            datePickerDialog.show()
        }
    }

    private fun addTaskIntoDataBase() {
        val task = Task(title = binding.title.text.toString(), date = calendar.time)
        TaskDatabase.getInstance().getTasksDao().insertTask(task)
        onTodoAddedListener?.onTodoAdded()
        dismiss()
    }

    private fun validateInputs(): Boolean {
        var isValid = true
        //       null                ""                                 "         "
        if (binding.title.text.isNullOrEmpty() || binding.title.text.isNullOrBlank()) {
            isValid = false
            binding.title.error = getString(R.string.required)
            return isValid
        } else {
            binding.title.error = null
        }
        if (binding.selectDateTv.text.isNullOrEmpty()) {
            isValid = false
            Toast.makeText(
                context,
                getString(R.string.you_should_select_task_date),
                Toast.LENGTH_SHORT
            ).show()
        }

        return isValid
    }

}