package ru.spb.banner.internal.presentation.store.bindactionsource

import kotlinx.coroutines.flow.flow
import ru.fabit.storecoroutines.BindActionSource
import ru.spb.banner.internal.presentation.store.action.BannerAction
import ru.spb.banner.internal.presentation.store.state.BannerState
import javax.inject.Inject

class UpdateBindActionSource @Inject constructor() : BindActionSource<BannerState, BannerAction>(
    requirement = { action -> action is BannerAction.Update },
    source = { state, action ->
        action as BannerAction.Update
        val current = action.items.maxByOrNull { it.zIndex }
        flow {
            if (current == null || state.currentItem == null || current != state.currentItem)
                emit(BannerAction.ShowItem(current))
        }
    },
    error = { BannerAction.Error(it) }
)