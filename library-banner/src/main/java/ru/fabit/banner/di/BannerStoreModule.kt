package ru.spb.banner.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import ru.fabit.storecoroutines.ErrorHandler
import ru.fabit.viewcontroller.ViewControllerComponent
import ru.fabit.viewcontroller.ViewControllerScoped
import ru.spb.banner.internal.presentation.store.BannerStore
import ru.spb.banner.internal.presentation.store.action.BannerAction
import ru.spb.banner.internal.presentation.store.bindactionsource.UpdateBindActionSource
import ru.spb.banner.internal.presentation.store.reducer.BannerReducer
import ru.spb.banner.internal.presentation.store.state.BannerState

@Module
@InstallIn(ViewControllerComponent::class)
class BannerStoreModule {

    @Provides
    @ViewControllerScoped
    fun provideStore(
        updateBindActionSource: UpdateBindActionSource,
        errorHandler: ErrorHandler,
    ): BannerStore {
        return BannerStore(
            state = BannerState(),
            reducer = BannerReducer(),
            errorHandler = errorHandler,
            bootstrapAction = BannerAction.Init,
            actionSources = listOf(),
            bindActionSources = listOf(updateBindActionSource),
            sideEffects = listOf(),
            actionHandlers = listOf()
        )
    }
}