package by.ebogatyrev.samples.firebaseremoteconfig.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.FetchRemoteConfigOut
import by.ebogatyrev.samples.firebaseremoteconfig.domain.usecase.FetchRemoteConfigUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val fetchRemoteConfigUseCase: FetchRemoteConfigUseCase): ViewModel() {

    private val _remoteConfig: MutableStateFlow<FetchRemoteConfigOut> = MutableStateFlow(FetchRemoteConfigOut.Progress());
    val remoteConfig: StateFlow<FetchRemoteConfigOut> = _remoteConfig

    fun fetchRemoteConfig() {
        viewModelScope.launch {
            fetchRemoteConfigUseCase.launch().collect { out ->
                _remoteConfig.emit(out)
            }
        }
    }
}