package ru.fabit.banner.presentation.store.reducer

import ru.fabit.banner.presentation.store.action.BannerAction
import ru.fabit.banner.presentation.store.state.BannerEvent
import ru.fabit.banner.presentation.store.state.BannerState
import ru.fabit.storecoroutines.EventsReducer

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