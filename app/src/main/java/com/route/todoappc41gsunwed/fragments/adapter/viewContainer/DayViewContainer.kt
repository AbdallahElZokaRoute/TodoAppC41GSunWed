package com.route.todoappc41gsunwed.fragments.adapter.viewContainer

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekDayBinder
import com.route.todoappc41gsunwed.R
import com.route.todoappc41gsunwed.databinding.ItemDayBinding
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

// CalendarDaysAdapter
@RequiresApi(Build.VERSION_CODES.O)            //  Inputs  -> Output
class CalendarWeekDaysBinder(
    val selectedColor: Int,
    val unSelectedColor: Int,
    val onDateSelected: (WeekDay) -> Unit,
) :
    WeekDayBinder<DayViewContainer> {
    var selectedDate: LocalDate? = null
    override fun bind(container: DayViewContainer, data: WeekDay) {
        container.weekDayTextView.text =
            data.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        container.monthDayTextView.text = "${data.date.dayOfMonth}"
        if (selectedDate == data.date) {
            // set color to blue
            container.weekDayTextView.setTextColor(selectedColor)
            container.monthDayTextView.setTextColor(selectedColor)
        } else {
            // set color to black
            container.weekDayTextView.setTextColor(unSelectedColor)
            container.monthDayTextView.setTextColor(unSelectedColor)
        }
        container.binding.root.setOnClickListener {
            onDateSelected(data)
        }
    }

    override fun create(view: View): DayViewContainer {
        val binding = ItemDayBinding.bind(view)
        return DayViewContainer(binding)
    }


}

//fun <T> add(num1: T, num2: T): T {
//
//    return num1 + num2
//}

class DayViewContainer(binding: ItemDayBinding) : ViewContainer(binding.root) {
    // With ViewBinding
    val binding = ItemDayBinding.bind(view)
    val weekDayTextView = binding.weekDayTextView
    val monthDayTextView = binding.monthDayTextView
}