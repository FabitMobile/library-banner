package ru.fabit.banner.presentation.store.state

import ru.fabit.banner.Banner

sealed interface BannerEvent {
    data class Error(val message: String) : BannerEvent
    data class Show(val currentItem: Banner?) : BannerEvent
}
