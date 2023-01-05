package by.ebogatyrev.samples.firebaseremoteconfig.ui.unavailable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.ebogatyrev.samples.firebaseremoteconfig.MainActivity
import by.ebogatyrev.samples.firebaseremoteconfig.R
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.FetchRemoteConfigOut
import by.ebogatyrev.samples.firebaseremoteconfig.domain.model.RemoteConfigModel
import by.ebogatyrev.samples.firebaseremoteconfig.ui.base.BaseFragment
import by.ebogatyrev.samples.firebaseremoteconfig.ui.base.BaseViewModel
import by.ebogatyrev.samples.firebaseremoteconfig.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ServiceUnavailableFragment: BaseFragment() {

    companion object {
        const val TAG = "ServiceUnavailable"
    }
    override val viewModel: ServiceUnavailableViewModel by viewModels()
    private lateinit var messageTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_service_unavailable, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messageTextView = view.findViewById<TextView>(R.id.messageTextView)
        view.findViewById<Button>(R.id.exitBtn).setOnClickListener {
            requireActivity().finish()
        }
        view.findViewById<Button>(R.id.retryBtn).setOnClickListener {
            viewModel.fetchRemoteConfig()
        }
    }

    override fun updateRemoteConfig(config: RemoteConfigModel) {
        if (!config.isUnavailable) {
            findNavController().navigate(R.id.action_global_mainFragment)
        } else {
            messageTextView.text = config.unavailableReasonMessage
        }
    }

}