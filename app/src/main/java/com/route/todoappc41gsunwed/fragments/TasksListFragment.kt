package com.route.todoappc41gsunwed.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.route.todoappc41gsunwed.R
import com.route.todoappc41gsunwed.clearTime
import com.route.todoappc41gsunwed.database.TaskDatabase
import com.route.todoappc41gsunwed.databinding.FragmentTaskListBinding
import com.route.todoappc41gsunwed.fragments.adapter.TasksAdapter
import com.route.todoappc41gsunwed.fragments.adapter.viewContainer.CalendarWeekDaysBinder
import com.route.todoappc41gsunwed.setDate
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar
import java.util.Date

class TasksListFragment : Fragment() {
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TasksAdapter
    private lateinit var calendarWeekDaysBinder: CalendarWeekDaysBinder
    private lateinit var calendar: Calendar

    // Callbacks ->
    // CalendarView -> Github -> RecyclerView ?
    // Date Selection or Task Filtration
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
        getAllTaskListFromDatabase()
    }

    fun getAllTaskListFromDatabase() {
        if (isVisible) {
            val context2 = context
            val context = requireContext()
        }
        if (!isHidden) {
            val context = requireContext()
        }
        val list = TaskDatabase.getInstance().getTasksDao().getAllTasks() //  1
        adapter.taskList = list
        adapter.notifyDataSetChanged()

    }

    fun getTasksByDate(date: Date) {
        val list = TaskDatabase.getInstance().getTasksDao().getTasksByDate(date)
        adapter.taskList = list
        adapter.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initCalendarView() {    // class WeekDaysAdapter : WeekDayBinder
        // 1- Interface callback
        val selectedColor = resources.getColor(R.color.blue, null)
        val unSelectedColor = resources.getColor(R.color.black, null)
        // Not selected Any Dates -> Get All Tasks
        // Selected Date -> Get Task by Date
        calendar = Calendar.getInstance()
        calendarWeekDaysBinder = CalendarWeekDaysBinder(selectedColor, unSelectedColor) { weekDay ->
            val currentSelection = calendarWeekDaysBinder.selectedDate
            if (currentSelection == weekDay.date) {
                // Reload this date so the dayBinder is called
                // and we can REMOVE the selection background.
                calendarWeekDaysBinder.selectedDate = null
                getAllTaskListFromDatabase()
                binding.weekCalendarView.notifyDateChanged(currentSelection)
            } else {
                calendarWeekDaysBinder.selectedDate = weekDay.date
                Log.e("TAG", "initCalendarView: Calendar Month ${calendar.get(Calendar.MONTH)}")
                Log.e("TAG", "initCalendarView: Local Date Month ${weekDay.date.monthValue}")
                val day = weekDay.date.dayOfMonth
                val month = weekDay.date.monthValue - 1
                val year = weekDay.date.year
                calendar.setDate(day, month, year)
                calendar.clearTime()


                Log.e("TAG", "initCalendarView: parameter : ${calendar.timeInMillis} ")
                getTasksByDate(calendar.time)
                //     12 - 2 - 2025    8:37:40PM
                //     12 - 2 - 2025    8:21:10PM

                binding.weekCalendarView.notifyDateChanged(weekDay.date)
                if (currentSelection != null) {
                    binding.weekCalendarView.notifyDateChanged(currentSelection)
                }
            }
            // Communication between fragments  Callbacks
        }
        binding.weekCalendarView.dayBinder = calendarWeekDaysBinder
        val currentDate = LocalDate.now()
        val currentMonth = YearMonth.now() // 01/2025 ->
        val startDate = LocalDate.now() // Adjust as needed
        val endDate = currentMonth.plusMonths(100).atEndOfMonth() // Adjust as needed
        val firstDayOfWeek = DayOfWeek.SATURDAY // Available from the library
        //              ->List < data object> -> RecyclerView(Week Calendar View)
        binding.weekCalendarView.setup(startDate, endDate, firstDayOfWeek)
        binding.weekCalendarView.scrollToWeek(currentDate)
    }
}
