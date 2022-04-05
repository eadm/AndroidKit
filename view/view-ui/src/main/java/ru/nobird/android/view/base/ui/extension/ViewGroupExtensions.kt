package ru.nobird.android.view.base.ui.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


/**
 * Inflate view group
 * */
fun ViewGroup.inflate(@LayoutRes resId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(this.context).inflate(resId, this, attachToRoot)