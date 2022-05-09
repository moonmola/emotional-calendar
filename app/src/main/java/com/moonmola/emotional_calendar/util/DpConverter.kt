package com.moonmola.emotional_calendar

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.View
import androidx.fragment.app.Fragment

fun Number.dpToPx(context: Context?) = context?.toPx(this) ?: this.toInt()

fun Activity?.toPx(dp: Number): Int = (this as? Context)?.toPx(dp) ?: dp.toInt()

fun Fragment?.toPx(dp: Number): Int = this?.context?.toPx(dp) ?: dp.toInt()

fun View?.toPx(dp: Number): Int = this?.context?.toPx(dp) ?: dp.toInt()

fun Context?.toPx(dp: Number): Int {
    return try {
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            this?.resources?.displayMetrics
        ).toInt()
    } catch (e: Exception) {
        dp.toInt()
    }
}