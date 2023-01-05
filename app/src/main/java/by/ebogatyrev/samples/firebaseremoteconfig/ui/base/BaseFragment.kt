package by.ebogatyrev.samples.firebaseremoteconfig.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.ebogatyrev.samples.firebaseremoteconfig.MainActivity
import by.ebogatyrev.samples.firebaseremoteconfig.R
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.FetchRemoteConfigOut
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.RemoteConfigModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    protected abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.remoteConfig.collect { out ->
                when (out) {
                    is FetchRemoteConfigOut.Success -> {
                        updateRemoteConfig(out.data)
                    }
                    is FetchRemoteConfigOut.Progress -> {}
                    is FetchRemoteConfigOut.Failure -> {
                       onLoadConfigFailed(out.error)
                    }
                }
            }
        }
    }

    open fun updateRemoteConfig(config: RemoteConfigModel) {
        if (config.isUnavailable) {
            findNavController().navigate(R.id.action_to_serviceUnavailable_global)
        }
    }

    open fun onLoadConfigFailed(error: Throwable) {
        error.printStackTrace();
        Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchRemoteConfig()
    }
}