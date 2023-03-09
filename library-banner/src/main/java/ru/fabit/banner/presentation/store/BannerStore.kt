package ru.spb.banner.internal.presentation.store

import ru.fabit.storecoroutines.*
import ru.spb.banner.internal.presentation.store.action.BannerAction
import ru.spb.banner.internal.presentation.store.reducer.BannerReducer
import ru.spb.banner.internal.presentation.store.state.BannerEvent
import ru.spb.banner.internal.presentation.store.state.BannerState

class BannerStore(
    state: BannerState,
    reducer: BannerReducer,
    errorHandler: ErrorHandler,
    bootstrapAction: BannerAction,
    actionSources: List<ActionSource<BannerAction>>,
    bindActionSources: List<BindActionSource<BannerState, BannerAction>>,
    sideEffects: List<SideEffect<BannerState, BannerAction>>,
    actionHandlers: List<ActionHandler<BannerState, BannerAction>>,
) : EventsStore<BannerState, BannerAction, BannerEvent>(
    startState = state,
    reducer = reducer,
    errorHandler = errorHandler,
    bootstrapAction = bootstrapAction,
    sideEffects = sideEffects,
    bindActionSources = bindActionSources,
    actionSources = actionSources,
    actionHandlers = actionHandlers
)