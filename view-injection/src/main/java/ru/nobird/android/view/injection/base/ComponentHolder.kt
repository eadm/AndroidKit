package ru.nobird.android.view.injection.base

interface ComponentHolder<out Component> {
    fun provideComponent(): Component
    fun releaseComponent()
}