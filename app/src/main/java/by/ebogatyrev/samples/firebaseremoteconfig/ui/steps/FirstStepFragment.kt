package by.ebogatyrev.samples.firebaseremoteconfig.ui.steps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.ebogatyrev.samples.firebaseremoteconfig.R
import by.ebogatyrev.samples.firebaseremoteconfig.ui.base.BaseFragment
import by.ebogatyrev.samples.firebaseremoteconfig.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstStepFragment: BaseFragment() {
    companion object {
        const val TAG = "FirstStepFragment"
    }

    override val viewModel: FirstStepViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_step_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.backBtn).setOnClickListener {
            findNavController().navigateUp()
        }
        view.findViewById<Button>(R.id.nextdBtn).setOnClickListener {
            findNavController().navigate(FirstStepFragmentDirections.actionFirstStepFragmentToSecondStepFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(MainFragment.TAG, "onResume")
    }
}