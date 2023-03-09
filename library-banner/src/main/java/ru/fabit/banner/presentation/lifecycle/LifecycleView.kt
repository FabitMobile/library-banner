package ru.spb.banner.internal.presentation.lifecycle

interface LifecycleView {
    fun onResume()
    fun onPause()
    fun onDestroy()
}