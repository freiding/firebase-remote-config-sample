package by.ebogatyrev.samples.firebaseremoteconfig.ui.unavailable

import by.ebogatyrev.samples.firebaseremoteconfig.domain.usecase.FetchRemoteConfigUseCase
import by.ebogatyrev.samples.firebaseremoteconfig.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServiceUnavailableViewModel @Inject constructor(
    private val fetchRemoteConfigUseCase: FetchRemoteConfigUseCase
): BaseViewModel(fetchRemoteConfigUseCase)