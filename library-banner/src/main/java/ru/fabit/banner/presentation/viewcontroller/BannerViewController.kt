package ru.fabit.banner.presentation.viewcontroller

import dagger.hilt.android.scopes.ViewScoped
import ru.fabit.banner.Banner
import ru.fabit.banner.presentation.store.BannerStore
import ru.fabit.banner.presentation.store.action.BannerAction
import ru.fabit.banner.presentation.store.state.BannerState
import ru.fabit.viewcontroller.StateView
import ru.fabit.viewcontroller.ViewControllerForView
import javax.inject.Inject

@ViewScoped
class BannerViewController @Inject constructor(
    store: BannerStore,
) : ViewControllerForView<BannerState, BannerAction, StateView<BannerState>>(store) {

    fun update(items: Set<Banner>) {
        dispatchAction(BannerAction.Update(items))
    }

    fun applyInsets() {
        dispatchAction(BannerAction.ApplyInsets)
    }
}