package ru.fabit.banner.presentation.view

import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun dpToPx(context: Context, dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    context.resources.displayMetrics
).toInt()

fun View.setOnApplyWindowInsetsCallback(func: (view: View, windowInsets: WindowInsetsCompat) -> WindowInsetsCompat) {
    ViewCompat.setOnApplyWindowInsetsListener(this, func)
    requestInsets()
}

fun View.requestInsets() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) {}
        })
    }
}