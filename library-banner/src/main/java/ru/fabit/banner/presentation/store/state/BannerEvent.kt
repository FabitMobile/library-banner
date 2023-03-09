package ru.spb.banner.internal.presentation.store.state

import ru.spb.banner.Banner

sealed interface BannerEvent {
    data class Error(val message: String) : BannerEvent
    data class Show(val currentItem: Banner?) : BannerEvent
}
