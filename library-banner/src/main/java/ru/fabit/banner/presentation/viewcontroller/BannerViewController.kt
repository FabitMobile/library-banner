package ru.spb.banner.internal.presentation.viewcontroller

import ru.fabit.viewcontroller.HiltViewController
import ru.fabit.viewcontroller.StateView
import ru.fabit.viewcontroller.ViewController
import ru.spb.banner.Banner
import ru.spb.banner.internal.presentation.store.BannerStore
import ru.spb.banner.internal.presentation.store.action.BannerAction
import ru.spb.banner.internal.presentation.store.state.BannerState
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