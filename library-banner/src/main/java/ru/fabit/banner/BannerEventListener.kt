package ru.spb.banner

interface BannerEventListener {
    fun positive(banner: Banner)

    fun negative(banner: Banner)
}