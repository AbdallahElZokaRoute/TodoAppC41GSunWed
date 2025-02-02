package com.route.todoappc41gsunwed.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.WeekDayBinder
import com.route.todoappc41gsunwed.R
import com.route.todoappc41gsunwed.database.TaskDatabase
import com.route.todoappc41gsunwed.database.model.Task
import com.route.todoappc41gsunwed.databinding.FragmentSettingsBinding
import com.route.todoappc41gsunwed.databinding.FragmentTaskListBinding
import com.route.todoappc41gsunwed.fragments.adapter.TasksAdapter
import com.route.todoappc41gsunwed.fragments.adapter.viewContainer.CalendarWeekDaysBinder
import com.route.todoappc41gsunwed.fragments.adapter.viewContainer.DayViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

class TasksListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TasksAdapter
    private var selectedDate: LocalDate? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initCalendarView()
        }
        adapter = TasksAdapter() // getItemCount => 0
        binding.tasksRecyclerView.adapter = adapter
        getTaskListFromDatabase()
    }

    fun getTaskListFromDatabase() {
        if (isVisible) {
            val context2 = context
            val context = requireContext()
            if (context2 != null)
                Toast.makeText(context2, "Hello World", Toast.LENGTH_LONG).show()

        }
        if (!isHidden) {
            val context = requireContext()
        }
        val list = TaskDatabase.getInstance().getTasksDao().getAllTasks() //  1
        adapter.taskList = list
        adapter.notifyDataSetChanged()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initCalendarView() {    // class WeekDaysAdapter : WeekDayBinder
        // 1- Interface callback
        binding.weekCalendarView.dayBinder = CalendarWeekDaysBinder { weekDay, container ->
            val currentSelection = selectedDate
            if (currentSelection == weekDay.date) {
                // Reload this date so the dayBinder is called
                // and we can REMOVE the selection background.
                selectedDate = null
                binding.weekCalendarView.notifyDateChanged(currentSelection)
            } else {
                selectedDate = weekDay.date
                binding.weekCalendarView.notifyDateChanged(weekDay.date)
                if (currentSelection != null) {
                    binding.weekCalendarView.notifyDateChanged(currentSelection)
                }
            }
        }
        val currentDate = LocalDate.now()
        val currentMonth = YearMonth.now() // 01/2025 ->
        val startDate = LocalDate.now() // Adjust as needed
        val endDate = currentMonth.plusMonths(100).atEndOfMonth() // Adjust as needed
        val firstDayOfWeek = DayOfWeek.SATURDAY // Available from the library
        binding.weekCalendarView.setup(startDate, endDate, firstDayOfWeek)
        binding.weekCalendarView.scrollToWeek(currentDate)
    }
}
