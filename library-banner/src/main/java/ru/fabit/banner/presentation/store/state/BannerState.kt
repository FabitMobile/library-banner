package ru.fabit.banner.presentation.store.state

import ru.fabit.banner.Banner
import ru.fabit.storecoroutines.EventsState

data class BannerState(
    val items: Set<Banner> = setOf(),
    val currentItem: Banner? = null
) : EventsState<BannerEvent>()
