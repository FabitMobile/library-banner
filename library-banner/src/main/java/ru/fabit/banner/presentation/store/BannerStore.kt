package ru.fabit.banner.presentation.store

import ru.fabit.banner.presentation.store.action.BannerAction
import ru.fabit.banner.presentation.store.reducer.BannerReducer
import ru.fabit.banner.presentation.store.state.BannerEvent
import ru.fabit.banner.presentation.store.state.BannerState
import ru.fabit.storecoroutines.*

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