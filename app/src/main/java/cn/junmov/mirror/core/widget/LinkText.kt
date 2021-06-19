package cn.junmov.mirror.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import cn.junmov.mirror.R

class LinkText(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val labelText: TextView

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.list_item_link, this, true)
        labelText = root.findViewById(R.id.list_item_link_label)
        context.theme.obtainStyledAttributes(attrs, R.styleable.LinkText, 0, 0).apply {
            try {
                labelText.text = getString(R.styleable.LinkText_nav_label)
            } finally {
                recycle()
            }
        }
    }

    fun setNavLink(dest: NavDirections) {
        this.setOnClickListener {
            it.findNavController().navigate(dest)
        }
    }

}