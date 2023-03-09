package ru.fabit.banner.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import ru.fabit.banner.presentation.store.BannerStore
import ru.fabit.banner.presentation.store.action.BannerAction
import ru.fabit.banner.presentation.store.bindactionsource.UpdateBindActionSource
import ru.fabit.banner.presentation.store.reducer.BannerReducer
import ru.fabit.banner.presentation.store.state.BannerState
import ru.fabit.storecoroutines.ErrorHandler
import ru.fabit.viewcontroller.ViewControllerComponent
import ru.fabit.viewcontroller.ViewControllerScoped

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