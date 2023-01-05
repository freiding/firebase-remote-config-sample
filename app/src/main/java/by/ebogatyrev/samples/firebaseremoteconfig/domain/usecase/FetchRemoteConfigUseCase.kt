package by.ebogatyrev.samples.firebaseremoteconfig.domain.usecase

import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.FetchRemoteConfigOut
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.RemoteConfigArgs
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.RemoteConfigModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FetchRemoteConfigUseCase @Inject constructor(private val remoteConfig: FirebaseRemoteConfig) {
    fun launch(): Flow<FetchRemoteConfigOut> = flow {
        emit(FetchRemoteConfigOut.loading())
        remoteConfig.fetchAndActivate().await()
        val isUnavailable = remoteConfig.getBoolean(RemoteConfigArgs.SERVICE_UNAVAILABLE)
        val message = remoteConfig.getString(RemoteConfigArgs.SERVICE_UNAVAILABLE_MESSAGE)
        val response = RemoteConfigModel(
            isUnavailable = isUnavailable,
            unavailableReasonMessage = message
        )
        emit(FetchRemoteConfigOut.success(response))
    }
    .catch { emit(FetchRemoteConfigOut.failure(it)) }
    .flowOn(Dispatchers.IO)
}