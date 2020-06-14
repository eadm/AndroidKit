package ru.nobird.android.view.injection.base

import javax.inject.Qualifier

annotation class RxScheduler {
    @Qualifier
    annotation class Background

    @Qualifier
    annotation class Main
}
