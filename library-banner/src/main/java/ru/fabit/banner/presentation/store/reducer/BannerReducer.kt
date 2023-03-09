package ru.spb.banner.internal.presentation.store.reducer

import ru.fabit.storecoroutines.EventsReducer
import ru.spb.banner.internal.presentation.store.action.BannerAction
import ru.spb.banner.internal.presentation.store.state.BannerEvent
import ru.spb.banner.internal.presentation.store.state.BannerState

class BannerReducer : EventsReducer<BannerState, BannerAction> {
    override fun reduce(
        state: BannerState,
        action: BannerAction
    ): BannerState {
        return when (action) {
            is BannerAction.Error -> state.copy().apply {
                action.error.message?.let {
                    addEvent(BannerEvent.Error(it))
                }
            }
            is BannerAction.Update -> state.copy(
                items = action.items
            )
            is BannerAction.ShowItem -> state.copy(
                currentItem = action.item
            ).apply {
                addEvent(BannerEvent.Show(action.item))
            }
            is BannerAction.ApplyInsets -> state.copy().apply {
                addEvent(BannerEvent.Show(state.currentItem))
            }
            else -> state.copy()
        }
    }

    override fun copy(state: BannerState) = state.copy()
}