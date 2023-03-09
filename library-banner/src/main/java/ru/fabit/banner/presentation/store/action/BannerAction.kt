package ru.spb.banner.internal.presentation.store.action

import ru.spb.banner.Banner

sealed interface BannerAction {
    object Init : BannerAction
    object Ignore : BannerAction
    object ApplyInsets : BannerAction
    data class Update(val items: Set<Banner>) : BannerAction
    data class ShowItem(val item: Banner? = null) : BannerAction
    data class Error(val error: Throwable) : BannerAction
}
