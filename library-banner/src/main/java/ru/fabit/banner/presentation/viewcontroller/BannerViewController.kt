package ru.fabit.banner.presentation.viewcontroller

import ru.fabit.banner.Banner
import ru.fabit.banner.presentation.store.BannerStore
import ru.fabit.banner.presentation.store.action.BannerAction
import ru.fabit.banner.presentation.store.state.BannerState
import ru.fabit.viewcontroller.HiltViewController
import ru.fabit.viewcontroller.StateView
import ru.fabit.viewcontroller.ViewController
import javax.inject.Inject

@HiltViewController
class BannerViewController @Inject constructor(
    store: BannerStore,
) : ViewController<BannerState, BannerAction, StateView<BannerState>>(store) {

    fun update(items: Set<Banner>) {
        dispatchAction(BannerAction.Update(items))
    }

    fun applyInsets() {
        dispatchAction(BannerAction.ApplyInsets)
    }
}