package by.ebogatyrev.samples.firebaseremoteconfig.domain.model

import by.ebogatyrev.samples.firebaseremoteconfig.domain.usecase.FetchRemoteConfigUseCase

sealed class FetchRemoteConfigOut {
    data class Success(
        val data: RemoteConfigModel
    ):FetchRemoteConfigOut()
    class Progress(): FetchRemoteConfigOut()
    data class Failure(val error: Throwable): FetchRemoteConfigOut()

    companion object {
        fun loading(): Progress = Progress();
        fun failure(e: Throwable): Failure = Failure(e);
        fun success(data: RemoteConfigModel): Success = Success(data)
    }
}
