package ru.fabit.banner

fun interface BannerListener {
    fun perform(action: BannerAction, banner: Banner)
}