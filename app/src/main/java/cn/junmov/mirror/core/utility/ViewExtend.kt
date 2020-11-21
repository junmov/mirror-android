package cn.junmov.mirror.core.utility

import android.widget.AutoCompleteTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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