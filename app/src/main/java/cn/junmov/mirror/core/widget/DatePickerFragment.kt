package cn.junmov.mirror.core.widget

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.time.LocalDate

class DatePickerFragment(
    private val initDate: LocalDate = LocalDate.now(), private val callback: (LocalDate) -> Unit
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(
            requireContext(), this,
            initDate.year,
            initDate.monthValue - 1,
            initDate.dayOfMonth
        )
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        callback(LocalDate.of(year, month + 1, dayOfMonth))
    }

    companion object {
        const val TAG = "datePicker"
    }
}