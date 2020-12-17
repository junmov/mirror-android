package cn.junmov.mirror.core.utility

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import cn.junmov.mirror.R
import cn.junmov.mirror.core.widget.SheetTitle
import cn.junmov.mirror.core.widget.TableSyncCard
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("noFilterText")
fun bindNoFilterText(view: AutoCompleteTextView, text: String?) {
    view.setText(text, false)
}

@BindingAdapter("onDone")
fun bindOnDone(view: SheetTitle, click: View.OnClickListener) {
    view.setOnDoneListener(click)
}

@BindingAdapter("hideKeyboardOnFocus")
fun bindHideKeyboardOnFocus(view: AutoCompleteTextView, shouldHidden: Boolean) {
    view.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        if (hasFocus == shouldHidden) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}

@BindingAdapter("notEmptyField")
fun bindNotEmptyField(view: TextInputLayout, isOk: Boolean) {
    view.isErrorEnabled = true
    if (isOk) {
        view.error = null
    } else {
        view.error = "必填字段"
    }
}

@BindingAdapter("isGone")
fun bindGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("arrayRes")
fun bindArrayRes(view: AutoCompleteTextView, array: Array<String>) {
    val adapter = ArrayAdapter(view.context, R.layout.support_simple_spinner_dropdown_item, array)
    view.setAdapter(adapter)
}

@BindingAdapter("onPush")
fun bindPush(view: TableSyncCard, click: View.OnClickListener) {
    view.onPush(click)
}
@BindingAdapter("syncAt")
fun bindSyncAt(view:TableSyncCard,value:String?){
    view.setSyncAt(value)
}