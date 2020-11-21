package cn.junmov.mirror.core.utility

import android.view.View
import android.widget.AutoCompleteTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

fun BottomSheetDialogFragment.setupDismiss(
    lifecycleOwner: LifecycleOwner, observeUpdated: LiveData<Boolean>
) {
    observeUpdated.observe(lifecycleOwner) { if (it) this.dismiss() }
}

inline fun AutoCompleteTextView.onClickItem(crossinline function: (Any) -> Unit) {
    this.setOnItemClickListener { parent, _, position, _ ->
        function(parent.getItemAtPosition(position))
    }
}

fun View.setupSnackBar(lifecycleOwner: LifecycleOwner, message: LiveData<Int>, time: Int) {
    message.observe(lifecycleOwner) {
        if (it != 0) Snackbar.make(this, it, time).show()
    }
}