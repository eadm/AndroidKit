package ru.nobird.android.view.base.ui.extension

import android.app.AlarmManager
import android.app.PendingIntent
import android.os.Build

/**
 * Send schedule message
 * */
fun AlarmManager.scheduleCompat(scheduleMillis: Long, interval: Long, pendingIntent: PendingIntent) {
    if (Build.VERSION.SDK_INT < 23) {
        if (Build.VERSION.SDK_INT >= 19) {
            setWindow(AlarmManager.RTC_WAKEUP, scheduleMillis, interval, pendingIntent)
        } else {
            set(AlarmManager.RTC_WAKEUP, scheduleMillis + interval / 2, pendingIntent)
        }
    } else {
        setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, scheduleMillis + interval / 2, pendingIntent)
    }
}