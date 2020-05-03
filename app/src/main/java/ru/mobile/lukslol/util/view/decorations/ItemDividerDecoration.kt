package ru.mobile.lukslol.util.view.decorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemDividerDecoration(context: Context, colorRes: Int, heightRes: Int) : RecyclerView.ItemDecoration() {

    private val paint = Paint().apply { color = ContextCompat.getColor(context, colorRes) }

    private val height = context.resources.getDimensionPixelSize(heightRes)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft.toFloat()
        val right = (parent.width - parent.paddingRight).toFloat()

        for (i in 0 until parent.childCount - 1) {
            val child = parent.getChildAt(i)
            val nextChild = parent.getChildAt(i + 1)

            val top = (child.bottom + (nextChild.top - child.bottom) / 2).toFloat()
            val bottom = top + height

            c.drawRect(left, top, right, bottom, paint)
        }
    }
}