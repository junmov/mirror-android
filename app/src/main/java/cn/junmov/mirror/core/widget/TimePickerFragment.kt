package cn.junmov.mirror.core.widget

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalTime

class TimePickerFragment(
    private val initTime: LocalTime, private val callback: (LocalTime) -> Unit
) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(requireContext(), this, initTime.hour, initTime.minute, true)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        callback(LocalTime.of(hourOfDay, minute, initTime.second))
    }

    companion object{
        const val TAG = "timePicker"
    }
}