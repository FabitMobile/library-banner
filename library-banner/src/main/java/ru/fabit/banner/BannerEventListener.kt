package ru.fabit.banner

interface BannerEventListener {
    fun positive(banner: Banner)

    fun negative(banner: Banner)
}