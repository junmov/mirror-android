package cn.junmov.mirror.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import cn.junmov.mirror.R

class SheetTitle(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val titleTextView: TextView
    private val btnDone: ImageView

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.sheet_title, this, true)
        titleTextView = root.findViewById(R.id.label_sheet_title)
        btnDone = root.findViewById(R.id.btn_sheet_title)
        context.theme.obtainStyledAttributes(attrs, R.styleable.SheetTitle, 0, 0).apply {
            try {
                titleTextView.text = getString(R.styleable.SheetTitle_title)
            } finally {
                recycle()
            }
        }
    }

    fun setOnDoneListener(click: OnClickListener) {
        btnDone.setOnClickListener(click)
    }
}