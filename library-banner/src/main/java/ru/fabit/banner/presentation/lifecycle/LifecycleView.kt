package ru.fabit.banner.presentation.lifecycle

interface LifecycleView {
    fun onResume()
    fun onPause()
    fun onDestroy()
}