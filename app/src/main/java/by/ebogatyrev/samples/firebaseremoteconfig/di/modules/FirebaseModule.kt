package by.ebogatyrev.samples.firebaseremoteconfig.di.modules

import by.ebogatyrev.samples.firebaseremoteconfig.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Singleton
    @Provides
    fun providesFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return Firebase.remoteConfig.also { remoteConfig ->
            remoteConfig.setDefaultsAsync(R.xml.firebase_remote_config)
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 10
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
        }
    }
}