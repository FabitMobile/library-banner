package ru.spb.banner.internal.presentation.store.state

import ru.fabit.storecoroutines.EventsState
import ru.spb.banner.Banner

data class BannerState(
    val items: Set<Banner> = setOf(),
    val currentItem: Banner? = null
) : EventsState<BannerEvent>()
