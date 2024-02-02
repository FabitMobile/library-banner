package ru.fabit.banner

abstract class Banner(open val zIndex: Int) {
    internal var listener: BannerListener? = null

    protected fun perform(bannerAction: BannerAction) {
        listener?.perform(bannerAction, this)
    }

    internal fun register(listener: BannerListener) {
        this.listener = listener
    }
}