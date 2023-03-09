package ru.spb.banner

abstract class Banner(open val zIndex: Int) {
    protected var listener: BannerEventListener? = null

    fun register(listener: BannerEventListener) {
        this.listener = listener
    }
}