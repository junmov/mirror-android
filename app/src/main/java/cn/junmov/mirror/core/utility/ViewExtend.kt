package cn.junmov.mirror.core.utility

import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cn.junmov.mirror.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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

fun View.setupNavigateUp(lifecycleOwner: LifecycleOwner, observeUpdated: LiveData<Boolean>) {
    observeUpdated.observe(lifecycleOwner) { if (it) this.findNavController().navigateUp() }
}

fun TextView.setString(@StringRes res: Int, vararg args: Any) {
    this.text = context.getString(res, *args)
}

fun View.navTo(navDirections: NavDirections) {
    this.setOnClickListener(
        Navigation.createNavigateOnClickListener(navDirections)
    )
}

fun View.setupInputDialog(title: String, positive: String, function: (String) -> Unit) {
    this.setOnClickListener {
        MaterialAlertDialogBuilder(it.context)
            .setTitle(title)
            .setView(R.layout.dialog_single_edit)
            .setPositiveButton(positive) { dialog, _ ->
                val input = (dialog as AlertDialog).findViewById<TextView>(R.id.dialog_single_text)
                function(input?.text.toString())
            }
            .setNegativeButton("取消", null)
            .show()
    }
}

fun Fragment.showInputDialog(title: String, positive: String, function: (String) -> Unit): Boolean {
    MaterialAlertDialogBuilder(this.requireContext())
        .setTitle(title)
        .setView(R.layout.dialog_single_edit)
        .setPositiveButton(positive) { dialog, _ ->
            val input = (dialog as AlertDialog).findViewById<TextView>(R.id.dialog_single_text)
            function(input?.text.toString())
        }
        .setNegativeButton("取消", null)
        .show()
    return true
}

fun Fragment.navTo(navDirections: NavDirections): Boolean {
    this.findNavController().navigate(navDirections)
    return true
}