package com.route.todoappc41gsunwed.fragments.adapter.viewContainer

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekDayBinder
import com.route.todoappc41gsunwed.databinding.ItemDayBinding
import java.time.format.TextStyle
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)            //  Inputs  -> Output
class CalendarWeekDaysBinder(val onDateSelected: (WeekDay, container: DayViewContainer) -> Unit) :
    WeekDayBinder<DayViewContainer> {

    override fun bind(container: DayViewContainer, data: WeekDay) {
        container.weekDayTextView.text =
            data.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        container.monthDayTextView.text = "${data.date.dayOfMonth}"
//        if (selectedDate == data.date) {
        // set color to blue
//        }else {
        // set color to black
//        }
        container.binding.root.setOnClickListener {
            onDateSelected(data, container)
        }
    }

    override fun create(view: View): DayViewContainer {
        return DayViewContainer(view)
    }


}


class DayViewContainer(view: View) : ViewContainer(view) {
    // With ViewBinding
    val binding = ItemDayBinding.bind(view)
    val weekDayTextView = binding.weekDayTextView
    val monthDayTextView = binding.monthDayTextView
}