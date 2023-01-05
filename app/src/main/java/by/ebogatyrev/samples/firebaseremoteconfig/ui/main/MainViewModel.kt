package by.ebogatyrev.samples.firebaseremoteconfig.ui.main

import androidx.lifecycle.ViewModel
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.FetchRemoteConfigOut
import by.ebogatyrev.samples.firebaseremoteconfig.domain.usecase.FetchRemoteConfigUseCase
import by.ebogatyrev.samples.firebaseremoteconfig.ui.base.BaseViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchConfigUseCase: FetchRemoteConfigUseCase
) : BaseViewModel(fetchConfigUseCase)