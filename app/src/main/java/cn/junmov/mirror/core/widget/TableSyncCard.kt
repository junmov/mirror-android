package cn.junmov.mirror.core.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import cn.junmov.mirror.R
import com.google.android.material.card.MaterialCardView

class TableSyncCard @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet,
    defStyle: Int = com.google.android.material.R.attr.materialCardViewStyle
) : MaterialCardView(context, attr, defStyle) {

    private val tableName: TextView
    private val syncAt: TextView
    private val btnPush: ImageButton
    private val btnPull: ImageButton

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.card_table_sync, this, true)
        tableName = root.findViewById(R.id.card_table_sync_name)
        syncAt = root.findViewById(R.id.card_table_sync_last_sync)
        btnPull = root.findViewById(R.id.card_table_sync_btn_pull)
        btnPush = root.findViewById(R.id.card_table_sync_btn_push)
        context.theme.obtainStyledAttributes(attr, R.styleable.TableSyncCard, 0, 0).apply {
            try {
                tableName.text = getString(R.styleable.TableSyncCard_table_name)
            } finally {
                recycle()
            }
        }
    }

    fun setSyncAt(str: String?) {
        syncAt.text = str
    }

    fun onPull(click: OnClickListener) {
        btnPull.setOnClickListener(click)
    }

    fun onPush(click: OnClickListener) {
        btnPush.setOnClickListener(click)
    }
}