package ru.fabit.banner

import androidx.lifecycle.ViewModel

internal class BannerViewModel : ViewModel() {
    var currentItem: Banner? = null
    var items = listOf<Banner>()
}